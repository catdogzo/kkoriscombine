package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class noticeUpdateServlet
 */
@WebServlet("/noticeUpdate.bo")
public class noticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noNum = Integer.parseInt(request.getParameter("noNum"));
		String noTitle = request.getParameter("title");
		String noCon = request.getParameter("content");
		
		Notice n = new Notice(noNum, noTitle, noCon, "admin");
		
		int result = new NoticeService().noticeUpdate(n);
		
		String page = null;
		if(result > 0) {
			page = "/noticeDetailView.bo?noNum="+noNum;
			request.setAttribute("n", n);
		} else {
			page = "/views/common/errorPage.jsp";
			request.setAttribute("msg", "notice update fail");
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
