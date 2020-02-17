package knBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import knBoard.model.service.KnService;
import knBoard.model.vo.KnBoard;


@WebServlet("/KnDetailServlet")
public class KnDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KnDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int knNum = Integer.parseInt(s);
		KnBoard kn = new KnService().selectBoard(knNum);
		
		ArrayList<Reply> list = new KnService();
		
				String page = null;

		if(kn != null) {
			page = "views/board/boardDetailView.jsp";
			request.setAttribute("board", b);
			request.setAttribute("list", list);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 조회에 실패했습니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
						
					
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
