package message.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.model.service.MessageService;
import message.model.vo.Message;
import message.model.vo.PageInfo;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/list.ms")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("에러니1");
		
		MessageService mService = new MessageService();
		//쪽지에서 가져 올 것. 리스트 개수
		
		int listCount = mService.getListCount(); 
		
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		
		currentPage = 1; //첫 번째 보여질 페이지 1

		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("여기 메시지 나오면 성공?");
		} else {
			System.out.println(currentPage);
			System.out.println("여기 메시지 나오면 실패 null");
			
		}
		limit  = 10;
		maxPage = (int)((double)listCount/limit + 0.9);
		
		startPage = ((int)((double)currentPage/limit + 0.9) -1) * 10 + 1;
		endPage = startPage + limit -1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<Message> mList = mService.selectList(currentPage);
		
		String page = null;
		
		if(mList != null) {
			page = "views/message/messageList.view.jsp";
			request.setAttribute("mList", mList);
			request.setAttribute("pi", pi);
		} else {
			page ="views/common/errorPage.jsp";
			request.setAttribute("msg", "쪽지 조회에 실패했다냥");
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
