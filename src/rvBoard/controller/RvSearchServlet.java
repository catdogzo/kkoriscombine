package rvBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.model.vo.Paging;
import photo.model.vo.Photo;
import rvBoard.model.dao.RvDAO;
import rvBoard.model.service.RvService;
import rvBoard.model.vo.RvBoard;


@WebServlet("/search.rv")
public class RvSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RvSearchServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchCategory = request.getParameter("searchCategory");
		String searchTag = "%"+request.getParameter("searchTag")+"%";
		// 두개의 서비스를 호출할 것이기 때문에 참조변수로 생성
	
		
		int posts;
		int listCount = new RvService().getListCount(searchCategory, searchTag);//게시판 리스트 개수
		int currentPage; 	//현재 페이지 표시
		int limit;			//한 페이지에 표시된 페이징 수
		int maxPage;		//전체 페이지 중 가장 마지막 페이지
		int startPage;		//페이징 된 페이지 중 시작페이지
		int endPage;		//페이징 된페이지중 마지막 페이지 
				
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// 페이지 전환 시 전달 받은 페이지로 currentPage 적용
		}
		limit = 10;
		posts = 15;
		maxPage = (int)((double)listCount/limit+ 0.9); 
		startPage = (((int)((double)currentPage/limit + 0.9)) - 1) * limit + 1; // currentPage
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
			ArrayList<Photo> pList = null;
			
			ArrayList<RvBoard> list = new RvService().RvSearchList(currentPage, searchCategory, searchTag);
			if(list != null) {
				pList = new RvService().rvPsearch(list);
			}			
			
			String page = null;
			searchTag = searchTag.substring(1, searchTag.length() -1);
			
			if(list != null) {
				page = "views/rvBoard/rvBoardList.jsp";
				request.setAttribute("rList", list);
				request.setAttribute("pList", pList);
				request.setAttribute("pg", pg);
				request.setAttribute("searchCategory", searchCategory);
				request.setAttribute("searchTag", searchTag);

			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "검색 실패");
			}
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
