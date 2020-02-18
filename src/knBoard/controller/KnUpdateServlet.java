package knBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import knBoard.model.service.KnService;
import knBoard.model.vo.KnBoard;


@WebServlet("/KnUpdateServlet")
public class KnUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KnUpdateServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int knNum = Integer.parseInt(request.getParameter("num"));
		String knTitle = request.getParameter("title");
		String knCon = request.getParameter("con");
		
		KnBoard kn = new KnBoard(num, title, con);
		
		int result = new KnService().updateKn(kn);

		
		String page = null;
		if(result > 0) {
			page = "/detail.bo?no=" + num;
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
