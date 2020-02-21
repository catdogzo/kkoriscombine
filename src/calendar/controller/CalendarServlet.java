package calendar.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.model.service.CalendarService;
import calendar.model.vo.Calendar;

/**
 * Servlet implementation class Calendar
 */
@WebServlet("/cal.li")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서버로 캘린더 보내기. 
		// 일정의 제목 입력, 내용입력, 시작과 종료 일시 받음.
		// 사용자가 예약을 한 경우-(우선 그 사용자의 예약 내용만 조회가 되야 한다. 
		// 그 예약 번호에 따라서 해당 병원 아이디를 불러오고 날짜, 예약이 뜸. 
		// 단 예약 번호의 날짜는 해당 월에 한해야 함. 
		
		// 1. 예약된 걸 가져온다. 병원명, 예약일시
		// 2. 일정 메모한걸 보낸다. 
		
		// 예약 된걸 가져오는 것 해보기. 
		// 회원 아이디를 통해 조회한다. 
		CalendarService cService = new CalendarService();
		
		// id값을 넘겨서 해당 아이디의 일정을 조회할 것이다. 
		String usId = request.getParameter("usId");
		
		ArrayList<Calendar> cal = new CalendarService().selectCal(usId);
		
		String page = null;
		if(cal != null) {
			page = "views/calendar/calendar.jsp";
			request.setAttribute("cal", cal);
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "캘린더 가져오기 실패");
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
