package knBoard.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import knBoard.model.service.KnService;
import knBoard.model.vo.KnBoard;


@WebServlet("/write.kn")
public class KnWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public KnWriteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String knTitle = request.getParameter("title");
		String knCon = request.getParameter("content");
		String usNick = session.getAttribute("loginUser").getUserId;
		Date sqlDate = null;
		
		KnBoard kn = new KnBoard(knTitle, knCon, usNick, sqlDate);
		
		int result = new KnService().insertBoard(kn);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/list.bo");		
		}else {
			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	
	}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
