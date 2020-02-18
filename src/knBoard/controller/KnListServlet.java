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
import common.model.vo.Paging;


@WebServlet("/KnListServlet")
public class KnListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public KnListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KnService service = new KnService();
		
		// 페이징 관련
		int listCount = service.getListCount();
		int currentPage; // 현재 페이지 표시
		int limit;		 // 한 페이지에 표시될 페이징 수
		int maxPage;	 // 전체 페이지 중 가장 마지막 페이지
		int startPage;   // 페이징 된 페이지 중 시작 페이지
		int endPage;	 // 페이징 된 페이지 중 마지막 페이지
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// 페이지 전환 시 전달 받은 페이지로 currentPage 적용				
		}
		
		maxPage = (int)((double)listCount/limit+ 0.9); 
		startPage = (((int)((double)currentPage/limit + 0.9)) - 1) * limit + 1; // currentPage
		endPage = startPage + limit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		Paging pg = new Paging(currentPage, listCount, limit, maxPage, startPage, endPage);

		ArrayList<KnBoard> list = service.selectList(currentPage);
		
		String page = null;
		if(list != null) {
			page = "views/board/boardListView.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pg", pg);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
