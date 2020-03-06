package allUser.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import allUser.model.service.AllUserService;
import hospital.model.service.HpService;
import user.model.service.UserService;

/**
 * Servlet implementation class PwdSearchServlet
 */
@WebServlet(name="PwdSearchServlet", urlPatterns="/searchPwd.au")
public class PwdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId").toLowerCase();
		String userName = request.getParameter("userName");
		String email = request.getParameter("email").toLowerCase(); // 받는 사람
		String temPwd = request.getParameter("temPwd"); // 임시 비밀번호 (암호화x)
		String newPwd = request.getParameter("newPwd"); // 임시 비밀번호 (암호화o)
		
		AllUserService aus = new AllUserService();
		String kind = aus.searchKind(userId);
		
		boolean confirmId = false; // 입력한 정보와 일치한 아이디인지
		
		if(kind.equals("USER")) {
			ArrayList<String> idList = new UserService().searchId(userName, email);
			for(String id : idList) {
				if(id.equals(userId)) {
					confirmId = true;
					break;
				}
			}
		} else if(kind.equals("HP")) {
			ArrayList<String> idList = new HpService().searchId(userName, email);
			for(String id : idList) {
				if(id.equals(userId)) {
					confirmId = true;
					break;
				}
			}
		}
		
		if(confirmId){
			/*char[] charArr = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			String temPwd = "";
			for(int i = 0; i < 6; i++) {
				int idx = (int)(charArr.length * Math.random());
				temPwd += charArr[idx];
			}*/
			
			int result = aus.updatePwd(userId, newPwd);
			PrintWriter out = response.getWriter();
			if(result > 0) {
				// 임시비밀번호 메일 발송
				final String sender = "kkoriscombine@naver.com"; // 보내는 사람 ID (Ex: @naver.com 까지..)
				final String senderPwd = "kkoris123!"; // 보내는 사람 Password			
				
				String title = "[꼬리스컴바인] 임시 비밀번호 발송";
				String contents = "회원(" + userId + ")님의 임시비밀번호는 " + temPwd + "입니다.<br>"
								+ "<font color='red'>로그인 후 비밀번호를 반드시 변경해주세요.</font>";
				String host = "smtp.naver.com"; // 사용하는 메일
				
				// Get the session object
				Properties props = new Properties();
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.auth", "true");

				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender, senderPwd);
					}
				});

				// Compose the message
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(sender));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

					// sender Email Address
//					message.setFrom("테스트메일 : <" + sender + ">");
					message.setFrom(sender);

					// Subject
					message.setSubject(title);

					// Text
					message.setText(contents, "UTF-8", "html");

					// send the message
					Transport.send(message);
					System.out.println("전송 완료!!!!");
					
//					response.sendRedirect("index.jsp");
					
					out.print("success");
				} catch (MessagingException e) {
					System.out.println("전송 실패!! ㅠㅠ");
					e.printStackTrace();
				}
			} else {
				out.print("fail");
			}	
		
			out.flush();
			out.close();
		} else {
			request.setAttribute("msg", "비밀번호 찾기 실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
