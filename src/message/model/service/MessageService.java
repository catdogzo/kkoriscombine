package message.model.service;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import message.model.dao.MessageDAO;
import message.model.vo.Message;

public class MessageService {

	public int getListCount() {
		Connection conn = getConnection();
		System.out.println("에러니?2");
		int result = new MessageDAO().getListCount(conn);
		close(conn);
		
		System.out.println(result); //0
		return result;
	}

	public ArrayList<Message> selectList(int currentPage) {
		Connection conn = getConnection();
		System.out.println("에러니?3");

		ArrayList<Message> mList = new MessageDAO().selectList(conn, currentPage);
		close(conn);
		System.out.println(mList);
		return mList;
	}

	public Message selectMessage(int msgNum) {
		Connection conn = getConnection();
		MessageDAO mDao = new MessageDAO();
		
		Message message =  mDao.selectMessage(conn, msgNum);
		close(conn);
		
		return message;
	}

}
