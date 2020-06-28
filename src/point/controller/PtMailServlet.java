package point.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.vo.User;

/**
 * Servlet implementation class SendMailServlet
 */
@WebServlet("/mail.pt")
public class PtMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PtMailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String usEmail = ((User)session.getAttribute("loginUser")).getUsEmail();		
 
		final String sender = "kkoriscombine@naver.com";
		final String password = "kkoris123!";

		String receiver = usEmail;
		String title = "꼬리스컴바인 쿠폰번호입니다";
		String image = "<br><img src='http://drive.google.com/uc?export=view&id=1clIu6Uep4c0qpOsnOjJsmWkWCZf6mi_z' style='width: 150px; height: 150px;'><br>";
		String comment = "<br>*사용시 해당 쿠폰을 제시해주세요.<br>";
		String coupon = "구매하신 " + request.getParameter("cName") + " 쿠폰의 쿠폰 번호입니다.<br>" + request.getParameter("coupon") + comment + image;
		String host = "smtp.naver.com";


		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session2 = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session2);
			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

			message.setFrom(sender);

			message.setSubject("[꼬리스컴바인] " + title);

			message.setText(coupon, "UTF-8", "html");

			Transport.send(message);
			System.out.println("전송 완료");

		} catch (MessagingException e) {
			System.out.println("전송 실패");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/list.pt");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
