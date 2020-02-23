package point.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import point.model.service.PtService;

@WebServlet("/coupon.pt")
public class PtCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PtCouponServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ptcName = request.getParameter("couponcate");
		int result = new PtService().ptCoupon(ptcName);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
