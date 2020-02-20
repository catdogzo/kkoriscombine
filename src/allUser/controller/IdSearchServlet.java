package allUser.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hospital.model.service.HpService;
import user.model.service.UserService;

/**
 * Servlet implementation class IdSearchServlet
 */
@WebServlet("/searchId.au")
public class IdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		
		ArrayList<String> usIdList = new UserService().searchId(userName, email);
		ArrayList<String> hpIdList = new HpService().searchId(userName, email);
		
		String page = null;
		if(usIdList != null) {
			request.setAttribute("idList", usIdList);
			page = "views/user/findId.jsp";
		} else if(hpIdList != null){
			request.setAttribute("idList", hpIdList);
			page = "views/user/findId.jsp";
		} else {
			request.setAttribute("msg", "아이디 찾기 실패");
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
