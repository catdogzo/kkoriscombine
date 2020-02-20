package rvBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rvBoard.model.service.RvService;
import rvBoard.model.vo.RvBoard;


@WebServlet("/write.rv")
public class RvWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RvWriteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int star = Integer.parseInt(request.getParameter("score"));
		HttpSession session = request.getSession();
		String rvTitle = request.getParameter("title");
		String rvCon = request.getParameter("content");
		String usNick = session.getAttribute("loginUser").getUserId;
		String hpName = request.getParameter("hpName");
		
		RvBoard rv = new RvBoard(rvTitle, rvCon, hpName, usNick, star);
		
		int result = new RvService().insertRv(rv);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/list.rv");		
		}else {
			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
