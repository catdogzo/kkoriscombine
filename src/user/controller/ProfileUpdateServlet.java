package user.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;

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
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/profile.up")
public class ProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) { // enctype이 multipart/form-data로 전송되었는지 확인
			int maxSize = 1024 * 1024 * 10; // 10Mbyte : 전송파일 용량 제한
			String root = request.getSession().getServletContext().getRealPath("/"); // 웹 서버 컨테이너 경로 추출
			String savePath = root + "thumbnail_uploadFiles/";
		
			MultipartRequest multipartRequest 
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			String usPhoto = "";		// 바뀐 파일의 이름을 저장할 ArrayList
			
			Enumeration<String> files = multipartRequest.getFileNames();
			// 폼에서 전송된 파일들의 이름 반환
			while(files.hasMoreElements()) {
				String name = files.nextElement(); // 전송 순서의 역순으로 파일을 가져옴
				
				if(multipartRequest.getFilesystemName(name) != null) {
					// getFilesystemName(name) : MyFileRenamePolicy의 rename메소드에서 작성한 대로 rename된 파일명
					usPhoto = multipartRequest.getFilesystemName(name);
				}
			}
			
			HttpSession session = request.getSession();
		
			String loginUserId = ((AllUser)session.getAttribute("loginAu")).getAuId();
			String userName = multipartRequest.getParameter("userName");
			String nickName = multipartRequest.getParameter("userNick");
			String phone = multipartRequest.getParameter("userPhone");
			String email = multipartRequest.getParameter("userEmail");
			String usGender = multipartRequest.getParameter("usGender");
			String userIntro = multipartRequest.getParameter("usIntro");
			String usBirth = multipartRequest.getParameter("usBirth");
			
			if(usPhoto.equals("")) {
				usPhoto = multipartRequest.getParameter("savedPhoto");
			}

			Date sqlDate = null;
			String[] dateArr = usBirth.split("-");
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1]) - 1;
			int day = Integer.parseInt(dateArr[2]);
			
			if(usBirth != "") {
				sqlDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
			} else {
				sqlDate = new Date(new GregorianCalendar().getTimeInMillis());
			}
			
			User user = new User();
			
			user.setUsId(loginUserId);
			user.setUsName(userName);
			user.setUsNick(nickName);
			user.setUsPhone(phone);
			user.setUsEmail(email);
			user.setUsGender(usGender);
			user.setUsIntro(userIntro);
			user.setUsPhoto(usPhoto);
			user.setUsBirth(sqlDate);
			//user.setUsBirth(usBirth);
			
			int result = new UserService().updateUser(user);
			String page = "";
			if(result > 0) {
				page = "profile.us";
				session.setAttribute("loginUser", user);
			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "회원 정보 수정에 실패하였습니다.");
			}
			request.getRequestDispatcher(page).forward(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
