package message.model.service;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

//import static common.JDBCTemplate.rollback;
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

}
