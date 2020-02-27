package rvBoard.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import allUser.model.vo.AllUser;
import common.FileRename;
import photo.model.vo.Photo;
import rvBoard.model.service.RvService;
import rvBoard.model.vo.RvBoard;


@WebServlet("/write.rv")
public class RvWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RvWriteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) { // enctype이 multipart/form-date로 전송되었는지 확인
			int maxSize = 1024 * 1024 * 10; // 10Mbtyte : 전송파일 용량 제한
			String root = request.getSession().getServletContext().getRealPath("/"); // 웹 서버 컨테이너 경로 추출
			String savePath = root + "thumbnail_uploadFiles/"; // 자신을 thumbnail_uploadFiles에 넣어놓기 위함
	
			MultipartRequest multipartRequest 
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new FileRename());
		
			ArrayList<String> saveFiles = new ArrayList<String>(); // 바뀐 파일의 이름을 저장할 ArrayList
			ArrayList<String> originFiles = new ArrayList<String>(); // 원본 파일의 이름을 저장할 ArrayList
		
			Enumeration<String> files = multipartRequest.getFileNames();	
			
			while(files.hasMoreElements()) {
				String name = files.nextElement();// 전송 순서의 역순으로 파일을 가져옴
				
				if(multipartRequest.getFilesystemName(name) != null) {
					// getFilesystemName(name) : MyFileRenamePolicy의 rename메소드에서 작성한 대로 rename된 파일명
					saveFiles.add(multipartRequest.getFilesystemName(name));
					originFiles.add(multipartRequest.getOriginalFileName(name));			
				}			
			}
		
			ArrayList<Photo> fileList = new ArrayList<Photo>();
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				Photo ph = new Photo();
				ph.setPhPath(savePath);	
				ph.setPhOrig(originFiles.get(i));
				ph.setPhChng(saveFiles.get(i));
				
				if(i == originFiles.size() - 1) {
					ph.setPhFnum(0);
				} else {
					ph.setPhFnum(1);
				}
				fileList.add(ph);
			}		
			
			int star = Integer.parseInt(multipartRequest.getParameter("score"));
			HttpSession session = request.getSession();
			String rvTitle = multipartRequest.getParameter("title");
			String rvCon = multipartRequest.getParameter("content");
			String usNick = ((AllUser)session.getAttribute("loginAu")).getAuId();
			String hpName = multipartRequest.getParameter("hospital");
			Date rvDate = Date.valueOf(multipartRequest.getParameter("date"));
			RvBoard rv = new RvBoard(rvTitle, rvCon, hpName, usNick, star, rvDate);
			int result = new RvService().insertRv(rv, fileList);
			
			
			if(result > 0) {			System.out.println(9);	
				response.sendRedirect(request.getContextPath() + "/list.rv");		
			}else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}	System.out.println(10);				
				request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
				RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
				view.forward(request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
