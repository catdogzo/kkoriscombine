package point.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import allUser.model.vo.AllUser;
import common.model.vo.Paging;
import point.model.service.PtService;
import point.model.vo.Point;

@WebServlet("/list.pt")
public class PtListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PtListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PtService service = new PtService();
		HttpSession session = request.getSession();
		String usId = ((AllUser)session.getAttribute("loginAu")).getAuId();
		
		// 페이징 관련
		int listCount = service.getListCount(usId);
	    int posts;          //현재 페이지에 표시될 게시글 개수
		int currentPage; // 현재 페이지 표시
		int limit = 0;		 // 한 페이지에 표시될 페이징 수
		int maxPage;	 // 전체 페이지 중 가장 마지막 페이지
		int startPage;   // 페이징 된 페이지 중 시작 페이지
		int endPage;	 // 페이징 된 페이지 중 마지막 페이지
		
		currentPage = 1;
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// 페이지 전환 시 전달 받은 페이지로 currentPage 적용
		}
		
		limit = 10;
		
		maxPage = (int)((double)listCount/limit + 0.9);
		startPage = (((int)((double)currentPage/limit + 0.9)) - 1) * limit + 1;
		endPage = startPage + limit - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}	  
			Paging pg = new Paging(currentPage, listCount, limit, maxPage, startPage, endPage);
			
			ArrayList<Point> list = service.selectList(currentPage, usId);
			
			String page = null;
			if(list != null) {
				page = "views/point/pointView.jsp";
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
