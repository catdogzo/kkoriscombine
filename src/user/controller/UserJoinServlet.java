package user.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import allUser.model.service.AllUserService;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserJoinServlet
 */
@WebServlet(name="UserJoinServlet", urlPatterns="/joinUser.us")
public class UserJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String nickName = request.getParameter("nickName").toLowerCase();
		String email = request.getParameter("email").toLowerCase();
		String gender = request.getParameter("gender");
		
		int year = Integer.parseInt(request.getParameter("bYear"));
		int month = Integer.parseInt(request.getParameter("bMonth"))-1;
		int day = Integer.parseInt(request.getParameter("bDay"));
		
		Date sqlDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		
		User user = new User(userId, userName, phone, nickName, email, sqlDate, gender);
		
		int result1 = new AllUserService().joinUser(userId, userPwd);
		
		String page = null;
		if(result1 > 0) {
			int result2 = new UserService().joinUser(user);
			if(result2 > 0) {
				request.setAttribute("user", user);
				page = "views/user/confirmJoin.jsp";
			}
			System.out.println(result2);
		} else {
			request.setAttribute("msg", "회원가입 실패");
			page = "views/common/errorPage.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
