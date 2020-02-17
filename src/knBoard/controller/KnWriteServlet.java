package knBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/write.kn")
public class KnWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public KnWriteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession sesson = request.getSession();
//		String knTitle = request.getParameter("title");
//		String knCon = request.getParameter("content");
//		String usNick = ((User)session.getAttribute("loginUser")).getUserId();
//		Date sqlDate = null;
//		
//		KnBoard kn = new KnBoard(knTitle, knCon, usNick, sqlDate);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
