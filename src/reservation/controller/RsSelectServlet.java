package reservation.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hospital.model.service.HpService;
import hospital.model.vo.Hospital;
import pet.model.service.PetService;
import pet.model.vo.Pet;
import reservation.model.service.RsService;
import reservation.model.vo.Reservation;

/**
 * Servlet implementation class RsSelectServlet
 */
@WebServlet("/select.rs")
public class RsSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rsNum = Integer.parseInt(request.getParameter("rsNum"));
		System.out.println(rsNum);
		Reservation reservation = new RsService().selectRs(rsNum);
		
		String page = null;
		if(reservation != null) {
			Hospital hp = new HpService().selectHp(reservation.getHpId());
			Pet pet = new PetService().selectPet(reservation.getPetNum());
			
			request.setAttribute("reservation", reservation);
			request.setAttribute("hp", hp);
			request.setAttribute("pet", pet);
			page = "views/hospital/detailRs.jsp";
		} else {
			request.setAttribute("msg", "예약 상세조회 실패");
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
