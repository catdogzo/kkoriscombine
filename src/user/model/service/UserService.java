package user.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

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
	
}
