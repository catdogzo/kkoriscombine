package rvBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import rvBoard.model.service.RvService;

@WebServlet("/insertlike.rv")
public class RvLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RvLikeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		int no = Integer.parseInt(request.getParameter("no"));
		int like = Integer.parseInt(request.getParameter("like"));
		int likeView = 0;
		if(like == 0) {
			new RvService().insertLike(writer, no);	
			likeView = new RvService().addLikeView(no, like);
		}else {
			likeView = new RvService().addLikeView(no, like);
		}
		if(likeView > 0) {
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(likeView, response.getWriter());		
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
