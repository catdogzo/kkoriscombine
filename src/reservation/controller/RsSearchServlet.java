package reservation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import reservation.model.service.RsService;
import reservation.model.vo.Reservation;

/**
 * Servlet implementation class RsSearchServlet
 */
@WebServlet("/search.rs")
public class RsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hpId = request.getParameter("hpId");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if(Integer.parseInt(month) < 10)
			month = "0" + month;
		String day = request.getParameter("day");
		if(Integer.parseInt(day) < 10)
			day = "0" + day;
		
		String date = year + "-" + month + "-" + day;
		
		ArrayList<Integer> hours = new RsService().searchRs(hpId, date);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(hours, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
