package allUser.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import allUser.model.service.AllUserService;
import allUser.model.vo.AllUser;
import hospital.model.service.HpService;
import hospital.model.vo.Hospital;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns="/login.au")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		AllUser au = new AllUser(userId, userPwd);
		System.out.println(userPwd);
		AllUser loginAu = new AllUserService().loginUser(au);
		
		if(loginAu != null) {
			HttpSession session = request.getSession();
//			session.setMaxInactiveInterval(1200);
			session.setAttribute("loginAu", loginAu);
			
			if(loginAu.getAuKind().equals("USER")) {
				User loginUser = new UserService().selectUser(loginAu.getAuId());
				session.setAttribute("loginUser", loginUser);
				System.out.println("loginUser + " + loginUser);
			} else if(loginAu.getAuKind().equals("HP")) {
				Hospital loginHp = new HpService().selectHp(loginAu.getAuId());
				session.setAttribute("loginHp", loginHp);
			}
			
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("msg", "로그인 실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
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
