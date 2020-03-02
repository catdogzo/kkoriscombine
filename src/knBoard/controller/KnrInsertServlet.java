package knBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import knBoard.model.service.KnService;
import knBoard.model.vo.KnReply;

@WebServlet("/insertReply.kn")
public class KnrInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KnrInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		String con = request.getParameter("content");
		int no = Integer.parseInt(request.getParameter("no"));

		KnReply knr = new KnReply(con, no, id);
		
		ArrayList<KnReply> list = new KnService().insertKnr(knr);
		
		response.setContentType("application/json); charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyy-MM-dd").create();
		gson.toJson(list, response.getWriter());	

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}