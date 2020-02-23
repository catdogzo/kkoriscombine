package knBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import knBoard.model.service.KnService;
import knBoard.model.vo.KnBoard;
import knBoard.model.vo.KnReply;
import photo.model.vo.Photo;


@WebServlet("/detail.kn")
public class KnDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KnDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		KnBoard kn = new KnService().selectKn(no);
		
		// 사진 불러오기
		ArrayList<Photo> pList = new KnService().selectPhoto(no);
		// 댓글 같이 불러오기
		ArrayList<KnReply> list = new KnService().selectKnr(no);
		String page = null;
		
		if(kn != null) {
			page = "views/knBoard/knBoardDetail.jsp";
			request.setAttribute("board", kn);
			request.setAttribute("list", list); // 댓글
			request.setAttribute("pList", pList); // 사진
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 조회에 실패했습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);							
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
