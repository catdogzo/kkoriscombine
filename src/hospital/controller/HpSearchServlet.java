package hospital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import hospital.model.service.HpService;
import hospital.model.vo.Hospital;

/**
 * Servlet implementation class LocationServlet
 */
@WebServlet("/search.hp")
public class HpSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HpSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HpService service = new HpService();
		
		String sidoGugun = "%" + request.getParameter("sido") +"%"+ request.getParameter("gugun") + "%";
		String medical = request.getParameter("medical");
		String hName = "%" + request.getParameter("hName") + "%";
		
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("loc", sidoGugun);
		dataMap.put("medical", medical);
		dataMap.put("hName", hName); 
		
		ArrayList<Hospital> hList = service.selectList(dataMap);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(hList, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
