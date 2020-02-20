package rvBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.model.vo.Paging;
import photo.model.vo.Photo;
import rvBoard.model.service.RvService;
import rvBoard.model.vo.RvBoard;


@WebServlet("/list.rv")
public class RvListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RvListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RvService service = new RvService();
		
		// 페이징 관련
		int listCount = service.getListCount();
	    int posts;          //현재 페이지에 표시될 게시글 개수
		int currentPage; // 현재 페이지 표시
		int limit = 0;		 // 한 페이지에 표시될 페이징 수
		int maxPage;	 // 전체 페이지 중 가장 마지막 페이지
		int startPage;   // 페이징 된 페이지 중 시작 페이지
		int endPage;	 // 페이징 된 페이지 중 마지막 페이지
		
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
		
		ArrayList<RvBoard> rList = service.selectList(currentPage);
		ArrayList<Photo> pList = service.selectPlist(currentPage);
		
		String page = null;
		if(rList != null && pList != null) {
			request.setAttribute("plist", pList);
			request.setAttribute("rlist", rList);
			page = "views/thumbnail/thumbnailListview.jsp";
		} else {
			request.setAttribute("msg", "사진 게시판 조회에 실패하였습니다.");
			page = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
		


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
