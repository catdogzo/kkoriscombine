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
		if(ServletFileUpload.isMultipartContent(request)) { 
			int maxSize = 1024 * 1024 * 10; 
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "thumbnail_uploadFiles/"; 
	
			MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new FileRename());
		
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


			ArrayList<String> detailImgId = new ArrayList<String>();
			for(int i = 0; i < 4; i++) {
				detailImgId.add(multipartRequest.getParameter("detailImgId" + i));
			}
			ArrayList<String> changeImg = new ArrayList<String>();
			for(int i = 0; i < 4; i++) {
				changeImg.add(multipartRequest.getParameter("cContent" + i));
			}
		
			ArrayList<Photo> changeFile = new ArrayList<Photo>();
			ArrayList<Photo> newInsertFile = new ArrayList<Photo>();
			
			
			for(int h = 0; h < fileList.size();) {
				for(int i = 0; i < 4; i++) {
					if(!detailImgId.get(i).equals("") && changeImg.get(i).equals("data")){
						fileList.get(h).setPhNum(Integer.parseInt(detailImgId.get(i)));
						changeFile.add(fileList.get(h));
						h++;
					} else if(detailImgId.get(i).equals("") && changeImg.get(i).equals("data")) {
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

		
			String page = "";
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
