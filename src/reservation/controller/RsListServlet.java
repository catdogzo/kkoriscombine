package reservation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.model.service.RsService;
import reservation.model.vo.ReservationInfo;
import user.model.vo.User;

/**
 * Servlet implementation class RsListServlet
 */
@WebServlet("/list.rs")
public class RsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		// DB의 HM_CATE값의 view 값 매칭한 map 생성
		HashMap<String, String> cateMap = new HashMap<String, String>();
		// 진료과목
		cateMap.put("HC1", "내과");
		cateMap.put("HC2", "외과");
		cateMap.put("HC3", "안과");
		cateMap.put("HC4", "치과");
		cateMap.put("HC5", "피부과");
		cateMap.put("HC6", "기타");
		// 검사
		cateMap.put("HI1", "기초 혈액검사");
		cateMap.put("HI2", "심장사상충 검사");
		cateMap.put("HI3", "혈액형 검사");
		cateMap.put("HI4", "항체가 검사");
		cateMap.put("HI5", "내시경");
		cateMap.put("HI6", "초음파");
		// 건강검진
		cateMap.put("HS1", "기초 종합검진");
		cateMap.put("HS2", "복합 종합검진");
		// 예방접종
		cateMap.put("HV1", "코로나");
		cateMap.put("HV2", "켄넬로프");
		cateMap.put("HV3", "광견병");
		cateMap.put("HV4", "인플루엔자");
		cateMap.put("HV5", "반려견 종합백신");
		cateMap.put("HV6", "반려묘 종합백신");
		// 중성화 수술
		cateMap.put("HZ1", "수컷");
		cateMap.put("HZ2", "암컷");
		cateMap.put("HZ3", "잠복고환");
		cateMap.put("HZ4", "기타");
		
		ArrayList<ReservationInfo> riList = new RsService().listRs(loginUser.getUsId());
		
		String page = null;
		if(riList != null) {
			for(ReservationInfo ri : riList) { // 진료코드 한글데이터로 셋팅
				ri.setHmCate(cateMap.get(ri.getHmCate()));
			}
			request.setAttribute("riList", riList);
			page = "views/hospital/listRs.jsp";
		} else {
			request.setAttribute("msg", "예약 내역 조회 실패");
			page = "views/common/errorPage.jsp";
		}
		
		RequestDispatcher out = request.getRequestDispatcher(page);
		out.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}