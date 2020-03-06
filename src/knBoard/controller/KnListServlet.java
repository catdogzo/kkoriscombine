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


@WebServlet("/list.kn")
public class KnListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public KnListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KnService service = new KnService();
		
		// 페이징 관련
		int listCount = service.getListCount();
	    int posts;     
		int currentPage; 
		int limit = 0;		
		int maxPage;	
		int startPage;  
		int endPage;	
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));			
		}
		
		limit = 10;
		posts = 15;
		maxPage = (int)((double)listCount/limit+ 0.9); 
		startPage = (((int)((double)currentPage/limit + 0.9)) - 1) * limit + 1;
		endPage = startPage + limit - 1;
		
		if(listCount%posts != 0) {
		    maxPage = (int)((double)listCount/posts) + 1;
		} else {
		  	maxPage = (int)((double)listCount/posts);
		}
  
		  startPage = (((int)((double)currentPage/limit + 0.9)) - 1) * limit + 1;
		  endPage = startPage + limit -1;
		  if(maxPage < endPage) {
		      endPage = maxPage;
		   }
		  
			Paging pg = new Paging(currentPage, listCount, limit, maxPage, startPage, endPage);
	
			ArrayList<KnBoard> list = service.selectList(currentPage);
			
			String page = null;
			if(list != null) {
				page = "views/knBoard/knBoardList.jsp";
				request.setAttribute("list", list);
				request.setAttribute("pg", pg);
			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "조회에 실패하였습니다.");
			}
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
			
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
