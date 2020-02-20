package allUser.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import allUser.model.dao.AllUserDAO;
import allUser.model.vo.AllUser;

public class AllUserService {

	public AllUser loginUser(AllUser au) {
		Connection conn = getConnection();
		AllUser loginUser = new AllUserDAO().loginUser(conn, au);
		
		close(conn);
		return loginUser;
	}

	public int idCheck(String userId) {
		Connection conn = getConnection();
		int result = new AllUserDAO().idCheck(conn, userId);
		
		close(conn);
		return result;
	}

	public int joinUser(String userId, String userPwd) {
		Connection conn = getConnection();
		int result = new AllUserDAO().joinUser(conn, userId, userPwd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int joinHp(String userId, String userPwd) {
		Connection conn = getConnection();
		int result = new AllUserDAO().joinHp(conn, userId, userPwd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public String searchKind(String inputId) {
		Connection conn = getConnection();
		String kind = new AllUserDAO().searchKind(conn, inputId);
		
		close(conn);
		return kind;
	}

	public int updatePwd(String userId, String newPwd) {
		Connection conn = getConnection();
		int result = new AllUserDAO().updatePwd(conn, userId, newPwd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

}
