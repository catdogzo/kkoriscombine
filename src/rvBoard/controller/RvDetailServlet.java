package rvBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photo.model.vo.Photo;
import rvBoard.model.service.RvService;
import rvBoard.model.vo.RvBoard;

@WebServlet("/detail.rv")
public class RvDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RvDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		RvBoard rv = new RvService().selectRv(no);
		
		// 사진 불러오기
		ArrayList<Photo> pList = new RvService().selectPhoto(no);	
		
/*		// 아이디 마스킹 처리
		String writer = rv.getUsId();
		String swriter = writer.substring(0, 2);
		for(int i = 0; i < writer.length(); i++) {
			swriter += "*";
		}
		rv.setUsId(swriter);*/
		
		String page = null;
		if(rv != null) {
			page = "views/rvBoard/rvBoardDetail.jsp";
			request.setAttribute("board", rv);
			request.setAttribute("pList", pList); 
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
