package hospital.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import allUser.model.service.AllUserService;
import hospital.model.service.HpService;
import hospital.model.vo.Hospital;

/**
 * Servlet implementation class HpJoinServlet
 */
@WebServlet("/joinHp.hp")
public class HpJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HpJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String hpName = request.getParameter("hpName");
		String hpDName = request.getParameter("hpDName");
		String phone = request.getParameter("phone");
		String location = "[" + request.getParameter("zipcode") + "] " + request.getParameter("location1") + " " + request.getParameter("location2");
		String start = request.getParameter("startTime");
		String end = request.getParameter("endTime");
		String lunch = request.getParameter("breakTime");
		
		Hospital hp = new Hospital(userId, hpName, hpDName, phone, location, start, end, lunch);
		
		int result1 = new AllUserService().joinHp(userId, userPwd);
		
		String page = null;
		if(result1 > 0) {
			int result2 = new HpService().joinHp(hp);
			
			if(result2 > 0) {
				request.setAttribute("hp", hp);
				page = "views/user/confirmJoin.jsp";
			}
		} else {
			request.setAttribute("msg", "병원 회원가입 실패");
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
