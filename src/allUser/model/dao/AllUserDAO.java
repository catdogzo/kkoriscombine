package allUser.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import allUser.model.vo.AllUser;

public class AllUserDAO {

	private Properties prop = new Properties();
	
	public AllUserDAO() {
		String fileName = AllUserDAO.class.getResource("/sql/alluser/alluser-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AllUser loginUser(Connection conn, AllUser au) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AllUser loginUser = null;
		
		String query = prop.getProperty("loginUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, au.getAuId());
			pstmt.setString(2, au.getAuPwd());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginUser = new AllUser(rs.getString("AU_ID"),
										rs.getString("AU_PWD"),
										rs.getString("AU_KIND"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginUser;
	}

	public int idCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String query = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
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

	public int joinUser(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("joinUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int joinHp(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("joinHp");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public String searchKind(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String kind = null;
		
		String query = prop.getProperty("searchKind");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				kind = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return kind;
	}

	public int updatePwd(Connection conn, String userId, String newPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
