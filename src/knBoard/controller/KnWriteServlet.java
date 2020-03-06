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
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import allUser.model.vo.AllUser;
import common.MyFileRenamePolicy;
import knBoard.model.service.KnService;
import knBoard.model.vo.KnBoard;
import photo.model.vo.Photo;


@WebServlet("/write.kn")
public class KnWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public KnWriteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			String root = request.getSession().getServletContext().getRealPath("/"); 
			String savePath = root + "thumbnail_uploadFiles/"; 
	
			MultipartRequest multipartRequest 
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			ArrayList<String> saveFiles = new ArrayList<String>();
			ArrayList<String> originFiles = new ArrayList<String>();
		
			Enumeration<String> files = multipartRequest.getFileNames();	
			
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				
				if(multipartRequest.getFilesystemName(name) != null) {
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
		
			HttpSession session = request.getSession();
			String title = multipartRequest.getParameter("title");
			String con = multipartRequest.getParameter("content");
			String usNick = ((AllUser)session.getAttribute("loginAu")).getAuId();
		
			KnBoard kn = new KnBoard(title, con, usNick);
		
			int result = new KnService().insertKn(kn, fileList);
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/list.kn");		
			} else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
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
