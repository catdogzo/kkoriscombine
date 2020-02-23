package knBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.FileRename;
import knBoard.model.service.KnService;
import knBoard.model.vo.KnBoard;
import photo.model.vo.Photo;


@WebServlet("/update.kn")
public class KnUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KnUpdateServlet() {
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
			
			int no = Integer.parseInt(multipartRequest.getParameter("no"));
			String title = multipartRequest.getParameter("title");
			String con = multipartRequest.getParameter("con");		
			KnBoard kn = new KnBoard(no, title, con);
						
	
			ArrayList<Photo> fileList = new ArrayList<Photo>();
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				Photo ph = new Photo();
				ph.setKnNum(no);
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

			// 가져온 사진 번호
			ArrayList<String> detailImgId = new ArrayList<String>();
			for(int i = 0; i < detailImgId.size(); i++) {
				detailImgId.add(multipartRequest.getParameter("detailImgId" + i));
			}
				
			
			ArrayList<Photo> changeFile = new ArrayList<Photo>();
			ArrayList<Photo> newInsertFile = new ArrayList<Photo>();
			
			for(int h = 0; h < fileList.size();) {
				for(int i = 0; i < detailImgId.size(); i++) {
					if(!detailImgId.get(i).equals("") && detailImgId.get(i).equals("data")) { // 바꾼 파일
						fileList.get(h).setPhNum((Integer.parseInt(detailImgId.get(i))));
						changeFile.add(fileList.get(h));
						h++;
					} else if(detailImgId.get(i).equals("") && detailImgId.get(i).equals("data")) { // 새로 넣은 파일
						newInsertFile.add(fileList.get(h));
						h++;
					}
				}
			}

			int result = 0;
			
			if(changeFile.isEmpty() && newInsertFile.isEmpty()) {
				result = new KnService().updateKn(kn);
			} else if(!changeFile.isEmpty() && newInsertFile.isEmpty()) {
				result = new KnService().updateKn(kn, changeFile);
			} else if(changeFile.isEmpty() && !newInsertFile.isEmpty()) {
				result = new KnService().updateKn(kn, newInsertFile);
			} else {
				result = new KnService().updateKn(kn, changeFile, newInsertFile);
			}			
			
	
			
			String page = null;
			if(result > 0) {
				page = "/detail.kn?no=" + no;
			} else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "게시글 수정에 실패하였습니다.");
			}		
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
