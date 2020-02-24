package reservation.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservation.model.service.RsService;
import reservation.model.vo.Reservation;

/**
 * Servlet implementation class RsCompleteServlet
 */
@WebServlet("/complete.rs")
public class RsCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsCompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hpId = request.getParameter("hpId");
		
		String date = request.getParameter("rsDate");
		String dateArr[] = date.split("-");
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]) - 1;
		int day = Integer.parseInt(dateArr[2]);
		int hour = Integer.parseInt(request.getParameter("rsTime"));
		Date dateDate = new Date(new GregorianCalendar(year, month, day, hour, 0, 0).getTimeInMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormat = sdf.format(dateDate);		
		Timestamp rsDate = Timestamp.valueOf(dateFormat); // 타임스탬프로 형변환 끝
				
		String hmCate = request.getParameter("hmCate");
		int petNum = Integer.parseInt(request.getParameter("pet"));
		String rsMemo = request.getParameter("rsMemo");
		
		Reservation rs = new Reservation(rsDate, rsMemo, petNum, hmCate, hpId);
		Reservation reservation = new RsService().insertRs(rs);
		
		String page = null;
		if(reservation != null) {
			request.setAttribute("reservation", reservation);
			page = "views/hospital/confirmRs.jsp";
		} else {
			request.setAttribute("msg", "예약 실패");
			page = "views/common/errorPage.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
