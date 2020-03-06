package point.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import point.model.service.PtService;

@WebServlet("/coupon.hp")
public class CouponValServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CouponValServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String coupon = request.getParameter("coupon");
		
		int result = new PtService().couponVal(coupon);
		
		response.setContentType("application/json; charset=UTF-8");		
		new Gson().toJson(result, response.getWriter());		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
