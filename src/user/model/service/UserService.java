package user.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import user.model.dao.UserDAO;
import user.model.vo.User;

public class UserService {

	public int nickCheck(String nickName) {
		Connection conn = getConnection();
		
		int result = new UserDAO().nickCheck(conn, nickName);
		
		close(conn);
		return result;
	}

	public int joinUser(User user) {
		Connection conn = getConnection();
		
		int result = new UserDAO().joinUser(conn, user);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public User selectUser(String auId) {
		Connection conn = getConnection();
		
		User user = new UserDAO().selectUser(conn, auId);
		
		close(conn);
		return user;
	}

	public ArrayList<String> searchId(String userName, String email) {
		Connection conn = getConnection();
		
		ArrayList<String> usIdList = new UserDAO().searchId(conn, userName, email);
		
		close(conn);
		return usIdList;
		
	}
	
}
