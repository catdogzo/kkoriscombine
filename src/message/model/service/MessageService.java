package message.model.service;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import message.model.dao.MessageDAO;
import message.model.vo.Message;

public class MessageService {

	public int getListCount() {
		Connection conn = getConnection();
		int result = new MessageDAO().getListCount(conn);
		close(conn);
		
		System.out.println(result); //0
		return result;
	}

	public ArrayList<Message> selectList(int currentPage) {
		Connection conn = getConnection();

		ArrayList<Message> mList = new MessageDAO().selectList(conn, currentPage);
		close(conn);
		return mList;
	}

	public Message selectMessage(int mNum) {
		Connection conn = getConnection();
		MessageDAO mDao = new MessageDAO();
		
		Message message =  mDao.selectMessage(conn, mNum);
		close(conn);
		
		return message;
	}

	public int deleteMessage(int mNum) {
		Connection conn = getConnection();
		MessageDAO mDAO = new MessageDAO();
		int result = mDAO.deleteMessage(conn, mNum);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Message selectSendMessage(int mNum) {
		Connection conn = getConnection();
		MessageDAO mDao = new MessageDAO();
		
		Message message =  mDao.selectSendMessage(conn, mNum);
		close(conn);
		
		return message;
	}

	public ArrayList<Message> selectSendList(int currentPages) {
		
		Connection conn = getConnection();

		ArrayList<Message> mLists = new MessageDAO().selectSendList(conn, currentPages);
		close(conn);
		return mLists;
		
	}

	public int getListCounts() {
		Connection conn = getConnection();
		int result = new MessageDAO().getListCounts(conn);
		close(conn);
		
		System.out.println(result); 
		return result;
	}



}
