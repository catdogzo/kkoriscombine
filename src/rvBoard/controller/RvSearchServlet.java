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
		
		int posts;
		int listCount = new RvService().getListCount(searchCategory, searchTag);
		int currentPage;
		int limit;
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
