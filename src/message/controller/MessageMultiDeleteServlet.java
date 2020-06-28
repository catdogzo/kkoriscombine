package message.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import allUser.model.vo.AllUser;
import message.model.service.MessageService;
import message.model.vo.Message;
import message.model.vo.PageInfo;

/**
 * Servlet implementation class MessageMultiDeleteServlet
 */
@WebServlet("/deleteM.ms")
public class MessageMultiDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageMultiDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkArr = request.getParameterValues("checkArr");
		
		int[] checkArrInt = new int[checkArr.length]; 
		for (int i = 0; i < checkArr.length; i++) {
			checkArrInt[i] = Integer.parseInt(checkArr[i]); 
		} 
		
		//이 값에 따라서 성공여부 출력
		int result = new MessageService().deleteM(checkArrInt);
		
		
		
		String result1 = "";
		if(result > 0) {
			result1 = "성공적으로 삭제";
			
		} else {
			result1 = "삭제 실패";
		}
		response.getWriter().println(result1);			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
