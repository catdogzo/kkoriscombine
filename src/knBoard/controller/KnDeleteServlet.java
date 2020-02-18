package knBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import knBoard.model.service.KnService;

@WebServlet("/KnDeleteServlet")
public class KnDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KnDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("knNum"));
		
		int result = new KnService().deleteKn(no);
		
		if(result > 0) {

			response.sendRedirect(request.getContextPath() + "/list.bo");
			
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
