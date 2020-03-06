package hospital.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import hospital.model.service.HpService;
import hospital.model.vo.HpMedical;

/**
 * Servlet implementation class HpFeeSearchServlet
 */
@WebServlet("/searchfee.hp")
public class HpFeeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HpFeeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hpId = request.getParameter("hpId");
		String cate = request.getParameter("cate");
		
		HpMedical hm = new HpService().searchFee(hpId, cate);
		
		JSONObject hmObj = null;
		if(hm != null) {
			hmObj = new JSONObject();
			hmObj.put("hmCate", hm.getHmCate());
			hmObj.put("hmMin", hm.getHmMin());
			hmObj.put("hmMax", hm.getHmMax());
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(hmObj);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
