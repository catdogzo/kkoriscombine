package user.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import allUser.model.vo.AllUser;
import user.model.service.UserService;

/**
 * Servlet implementation class PwdUpdateServlet
 */
@WebServlet(name="PwdUpdateServlet" ,urlPatterns="/updatePwd.us")
public class PwdUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		String userId = ((AllUser)session.getAttribute("loginAu")).getAuId();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", userId);
		map.put("old", userPwd);
		map.put("new", newPwd);
		int result = 0;
		
		result = new UserService().updatePwd(map);

		if(result > 0) {
			response.sendRedirect("views/user/profile.jsp");
		} else {
			request.setAttribute("msg", "비밀번호 수정에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
