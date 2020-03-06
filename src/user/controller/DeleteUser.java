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

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/delete.us")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = ((AllUser)session.getAttribute("loginAu")).getAuId();
		
		int result = new UserService().deleteUser(userId);
		
		String page = null;
		if(result > 0) {
			page = "index.jsp";
			request.setAttribute("msg", "회원탈퇴를 하였습니다.");
			
			session.invalidate();
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "회원탈퇴에 실패하였습니다.");
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
