package point.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import allUser.model.vo.AllUser;
import point.model.service.PtService;
import point.model.vo.PtUse;

@WebServlet("/coupon.pt")
public class PtCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PtCouponServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String ptcName = request.getParameter("couponcate");
		ArrayList<PtUse> ptu = null;
		String usId = ((AllUser)session.getAttribute("loginAu")).getAuId();		
		int result = new PtService().ptCoupon(ptcName, usId);
		
		if(result > 0) {
			ptu = new PtService().selCoupon(usId);
		}
		
		String page = null;
		
	    PrintWriter out = response.getWriter();
	    out.println("<html><body>");
	    out.println("<script type='text/javascript'>");
	    out.println("window.open('views/point/couponResult.jsp=?' + ptu)");
	    out.println("</script>");
	    out.println("</body></html>");
		
		if(ptu != null) {
			page = "views/point/couponResult.jsp";
			request.setAttribute("ptu", ptu);
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "쿠폰 구매에 실패했습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
