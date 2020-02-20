package rvBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rvBoard.model.service.RvService;
import rvBoard.model.vo.RvBoard;


@WebServlet("/update.rv")
public class RvUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RvUpdateServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String con = request.getParameter("con");
		RvBoard rv = new RvBoard(no, title, con);
		
		int result = new RvService().updateRv(rv);
		
		String page = null;
		if(result > 0) {
			page = "/detail.rv?no=" + no;
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 수정에 실패하였습니다.");
		}		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
