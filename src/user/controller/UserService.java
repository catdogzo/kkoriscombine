package user.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

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

	public User selectUser(String loginUserId) {
		Connection conn = getConnection();
		
		User user = new UserDAO().selectUser(conn, loginUserId);
		
		close(conn);
		return user;
	}

	public ArrayList<String> searchId(String userName, String email) {
		Connection conn = getConnection();
		
		ArrayList<String> usIdList = new UserDAO().searchId(conn, userName, email);
		
		close(conn);
		return usIdList;
		
	}

	public int updateUser(User user) {
		Connection conn = getConnection();
		
		UserDAO uDAO = new UserDAO();
		
		int result = uDAO.updatePrImage(conn, user);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public int deleteUser(String userId) {
		Connection conn = getConnection();
		UserDAO uDAO = new UserDAO();
		int result = uDAO.deleteUser(conn, userId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int updatePwd(HashMap<String, String> map) {
		Connection conn = getConnection();
		
		UserDAO uDAO = new UserDAO();
		int result = uDAO.updatePwd(conn, map);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public int updatePrImage(User user) {
		Connection conn = getConnection();
		UserDAO uDAO = new UserDAO();
		int result = uDAO.updatePrImage(conn, user);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	
	
	
}
