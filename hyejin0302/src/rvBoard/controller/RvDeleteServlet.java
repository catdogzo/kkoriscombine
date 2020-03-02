package rvBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rvBoard.model.service.RvService;

@WebServlet("/delete.rv")
public class RvDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RvDeleteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = new RvService().deleteRv(no);
		System.out.print(no);
		
		if(result > 0) {

			response.sendRedirect(request.getContextPath() + "/list.rv");
			
		}else {

			request.setAttribute("msg", "게시글 삭제에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
