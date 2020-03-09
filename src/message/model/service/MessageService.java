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

	public int getListCount(String rsgId) {
		Connection conn = getConnection();
		int result = new MessageDAO().getListCount(conn, rsgId);
		close(conn);
		
		System.out.println("쪽지 몇개니? + " + result); //0
		return result;
	}

	public ArrayList<Message> selectList(int currentPage, String rsgId) {
		Connection conn = getConnection();

		ArrayList<Message> mList = new MessageDAO().selectList(conn, currentPage, rsgId);
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

	public ArrayList<Message> selectSendList(int currentPages, String ssgId) {
		
		Connection conn = getConnection();

		ArrayList<Message> mLists = new MessageDAO().selectSendList(conn, currentPages, ssgId);
		close(conn);
		return mLists;
		
	}

	public int getListCounts(String ssgId) {
		Connection conn = getConnection();
		int result = new MessageDAO().getListCounts(conn, ssgId);
		close(conn);
		
		System.out.println(result); 
		return result;
	}

	public int sendMessage(String rsgId, String ssgId, String title, String con) {
		Connection conn = getConnection();
		MessageDAO mDAO = new MessageDAO();
		
		int result = mDAO.insertMessage(conn, rsgId, ssgId, title, con);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);	
		}
		return result;
	}

	public int deleteM(int[] checkArrInt) {
		Connection conn = getConnection();
		MessageDAO mDAO = new MessageDAO();
		
		int result = mDAO.deleteM(conn, checkArrInt);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public int deleteSM(int[] checkArrInt) {
		Connection conn = getConnection();
		MessageDAO mDAO = new MessageDAO();
		
		int result = mDAO.deleteSM(conn, checkArrInt);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public int reSendMessage(String rsgId, String ssgId, String title, String con) {
		Connection conn = getConnection();
		MessageDAO mDAO = new MessageDAO();
		
		int result = mDAO.reSendMessage(conn, rsgId, ssgId, title, con);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);	
		}
		return result;
	}




}
