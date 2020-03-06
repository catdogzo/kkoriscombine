package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import allUser.model.vo.AllUser;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class ProfileDetailServlet
 */
@WebServlet("/profile.us")
public class ProfileDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String loginUserId = ((AllUser)session.getAttribute("loginAu")).getAuId();
		User user = new UserService().selectUser(loginUserId);
		
		String page = null;
		if(user != null) {
			page = "views/user/profile.jsp";
			request.setAttribute("user", user);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "내 정보 조회에 실패하였습니다.");
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
