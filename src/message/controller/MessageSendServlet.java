package message.controller;

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
import message.model.service.MessageService;
import message.model.vo.Message;
import message.model.vo.PageInfo;

/**
 * Servlet implementation class MessageSendServlet
 */
@WebServlet("/listSend.ms")
public class MessageSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String ssgId = ((AllUser)session.getAttribute("loginAu")).getAuId();
		
		System.out.println("ssgId서블릿 + " + ssgId);

		
		MessageService mService = new MessageService();
		int listCounts = mService.getListCounts(ssgId); 
		
		int currentPages;
		int limits;
		int maxPages;
		int startPages;
		int endPages;
		
		currentPages = 1; //첫 번째 보여질 페이지 1

		if(request.getParameter("currentPages") != null) {
			currentPages = Integer.parseInt(request.getParameter("currentPages"));
		} 
		limits  = 10;
		maxPages = (int)((double)listCounts/limits + 0.9);
		
		startPages = ((int)((double)currentPages/limits + 0.9) -1) * 10 + 1;
		endPages = startPages + limits -1;
		
		if(maxPages < endPages) {
			endPages = maxPages;
		}
		
		PageInfo pi = new PageInfo(currentPages, listCounts, limits, maxPages, startPages, endPages);
		
		ArrayList<Message> mLists = mService.selectSendList(currentPages, ssgId);
		
		String page = null;
		
		if(mLists != null) {
			page = "views/message/messageListSendView.jsp";
			request.setAttribute("mLists", mLists);
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
