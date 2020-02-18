package user.model.dao;

import static common.JDBCTemplate.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import user.model.vo.User;

public class UserDAO {
	private Properties prop = new Properties();
	
	public UserDAO() {
		String filename = UserDAO.class.getResource("/sql/user/user-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int nickCheck(Connection conn, String nickName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String query = prop.getProperty("nickCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int joinUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("joinUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUsId());
			pstmt.setString(2, user.getUsName());
			pstmt.setString(3, user.getUsPhone());
			pstmt.setString(4, user.getUsNick());
			pstmt.setString(5, user.getUsEmail());
			pstmt.setDate(6, user.getUsBirth());
			pstmt.setString(7, user.getUsGender());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
}
