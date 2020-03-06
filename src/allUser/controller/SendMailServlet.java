package allUser.controller;

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

/**
 * Servlet implementation class SendMailServlet
 */
@WebServlet("/send.do")
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. 전달받은 값 인코딩
		// - 전송할 값은 html이 아닌 다른 프로토콜로 전송할 것이기 때문에
		// 현재는 별도 인코딩을 하지 않습니다.
//		request.setCharacterEncoding("UTF-8");
 
		final String sender = "kkoriscombine@naver.com"; // 보내는 사람 ID (Ex: @naver.com 까지..)
		final String password = "kkoris123!"; // 보내는 사람 Password

		String receiver = request.getParameter("receiver"); // 받는 사용자 (Ex: @naver.com 까지..)
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String host = "smtp.naver.com"; // 사용하는 메일

		System.out.println("---------recv Data Check--------");
		System.out.println("recvID : " + receiver);
		System.out.println("title : " + title);
		System.out.println("content : " + contents);
		System.out.println("--------------------------");

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

			// sender Email Address
//			message.setFrom("테스트메일 : <" + sender + ">");
			message.setFrom(sender);

			// Subject
			message.setSubject("[꼬리스컴바인] " + title);

			// Text
			message.setText(contents, "UTF-8", "html");

			// send the message
			Transport.send(message);
			System.out.println("전송 완료!!!!");

		} catch (MessagingException e) {
			System.out.println("전송 실패!! ㅠㅠ");
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
