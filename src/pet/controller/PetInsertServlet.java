package pet.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import allUser.model.vo.AllUser;
import pet.model.service.PetService;
import pet.model.vo.Pet;

/**
 * Servlet implementation class PetInsertServlet
 */
@WebServlet("/insertProfile.pe")
public class PetInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// int petNum = Integer.parseInt(request.getParameter("petNum"));
		String petName = request.getParameter("petName");
		
		int year = Integer.parseInt(request.getParameter("bYear"));
		int month = Integer.parseInt(request.getParameter("bMonth"))-1;
		int day = Integer.parseInt(request.getParameter("bDay"));
		
		Date sqlDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		
		String petGender = request.getParameter("petGender");
		String petCate = request.getParameter("petCate");
		String petSpec = request.getParameter("petSpec");
		Double petWeight = Double.parseDouble(request.getParameter("petWeight"));
		String petVacc = request.getParameter("petVacc");
		String petNeut = request.getParameter("petNeut");
		String loginUserId = ((AllUser)session.getAttribute("loginAu")).getAuId();
		
		Pet pet = new Pet();
		
		pet.setPetName(petName);
		pet.setPetBirth(sqlDate);
		pet.setPetGender(petGender);
		pet.setPetCate(petCate);
		pet.setPetSpec(petSpec);
		pet.setPetWeight(petWeight);
		pet.setPetVacc(petVacc);
		pet.setPetNeut(petNeut);
		pet.setUsId(loginUserId);
		
		int result = new PetService().insertPet(pet);
		String page = "";
		if(result > 0) {
			page = "views/user/myPetList.jsp";
			request.setAttribute("msg", "반려동물 등록에 성공하였습니다.");
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "반려동물 등록에 실패하였습니다.");
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
