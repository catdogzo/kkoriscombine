package pet.controller;

import java.io.IOException;

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
 * Servlet implementation class PetDetailServlet
 */
@WebServlet("/detail.pet")
public class PetDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int petNum = Integer.parseInt(request.getParameter("petNum").trim());
		Pet pet = new PetService().selectPet(petNum);
		String page = null;
		if(pet != null) {
			page = "views/user/myPetDetail.jsp";
			request.setAttribute("pet", pet);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "반려동물 상세보기에 실패하였습니다.");
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
