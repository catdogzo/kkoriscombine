package knBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import allUser.model.vo.AllUser;
import knBoard.model.service.KnService;
import knBoard.model.vo.KnReply;

@WebServlet("/insertReply.kn")
public class KnrInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KnrInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String usId = ((AllUser)session.getAttribute("loginAu")).getAuId(); 
		String id= request.getParameter("id");
		String con = request.getParameter("content");
		int no = Integer.parseInt(request.getParameter("no"));
		KnReply knr = new KnReply();
		knr.setUsId(id);
		knr.setKnrCon(con);
		knr.setKnNum(no);
		
		ArrayList<KnReply> list = new KnService().insertKnr(knr, usId);
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(list, response.getWriter());	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
