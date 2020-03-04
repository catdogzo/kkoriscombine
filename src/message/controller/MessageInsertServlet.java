package message.controller;

import java.io.IOException;

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

/**
 * Servlet implementation class MessageInsertServlet
 */
@WebServlet("/insert.ms")
public class MessageInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String rsgId = request.getParameter("rsgId");
		String ssgId = ((AllUser)session.getAttribute("loginAu")).getAuId();
		String title = request.getParameter("title");
		String con = request.getParameter("con");
		
		System.out.println("titleS서블릿 + " + title);
		System.out.println("conS서블릿 + " + con);
		System.out.println("rsgId서블릿 + " + rsgId);
		System.out.println("ssgId서블릿 + " + ssgId);
		
		Message message = new Message();
		
		message.setRsgId(rsgId);
		message.setRsgId(ssgId);
		message.setMsgTitle(title);
		message.setMsgCon(con);
		
		int result = new MessageService().sendMessage(rsgId, ssgId, title, con);
		
		if(result > 0) {
			response.sendRedirect("list.ms");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg", "쪽지 전송 실패했습니다.");
			view.forward(request, response);
			
		}
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
