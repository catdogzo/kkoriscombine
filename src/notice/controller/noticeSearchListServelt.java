package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.PageInfo;

/**
 * Servlet implementation class noticeSearchListServelt
 */
@WebServlet("/noticeSearchList.bo")
public class noticeSearchListServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noticeSearchListServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchCategory = request.getParameter("searchCategory");
		String searchTag = "%"+request.getParameter("searchTag")+"%";
		// 두개의 서비스를 호출할 것이기 때문에 참조변수로 생성
		int posts;
		int listCount = new NoticeService().getListCount(searchCategory, searchTag);//게시판 리스트 개수
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
		
		posts = 15;
		limit = 10;
		if(listCount%posts != 0) {
			maxPage = (int)((double)listCount/posts) + 1;
		} else {
			maxPage = (int)((double)listCount/posts);
		}
		startPage = ((int)((double)currentPage/limit + 0.9) - 1) * limit + 1; // currentPage
		endPage = startPage + limit -1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
				
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		//-------------------------------------------------------------
		
		ArrayList<Notice> list = new NoticeService().noticeSearchList(currentPage, searchCategory, searchTag);
		String page = null;
		//searchTag보내기 전에 %tag%에서 %를 뺴주기
		searchTag = searchTag.substring(1, searchTag.length() -1);
		
		if(list == null) {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "notice searchlist fail");
		} else {
			page = "views/notice/noticeListView.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("searchCategory", searchCategory);
			request.setAttribute("searchTag", searchTag);
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
