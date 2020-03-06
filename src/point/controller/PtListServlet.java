package point.controller;

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
import common.model.vo.Paging;
import point.model.service.PtService;
import point.model.vo.Point;

@WebServlet("/list.pt")
public class PtListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PtListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PtService service = new PtService();
		HttpSession session = request.getSession();
		String usId = ((AllUser)session.getAttribute("loginAu")).getAuId();
		

			ArrayList<Point> list = service.selectList(usId);
			
			String page = null;
			if(list != null) {
				page = "views/point/pointView.jsp";
				request.setAttribute("list", list);
			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "포인트 조회에 실패하였습니다.");
			}
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);			
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
