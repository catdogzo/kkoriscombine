package hospital.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import hospital.model.vo.Hospital;

public class HpDAO {

	private Properties prop = new Properties();
	
	public HpDAO() {
		String fileName = HpDAO.class.getResource("/sql/hospital/hospital-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int joinHp(Connection conn, Hospital hp) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("joinHp");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, hp.getHpId());
			pstmt.setString(2, hp.getHpName());
			pstmt.setString(3, hp.getHpDName());
			pstmt.setString(4, hp.getHpPhone());
			pstmt.setString(5, hp.getHpEmail());
			pstmt.setString(6, hp.getHpLoc());
			pstmt.setString(7, hp.getHpStart());
			pstmt.setString(8, hp.getHpEnd());
			pstmt.setString(9, hp.getHpLunch());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Hospital selectHp(Connection conn, String auId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Hospital hp = null;
		
		String query = prop.getProperty("selectHp");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, auId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				hp = new Hospital(rs.getString("HP_ID"),
								  rs.getString("HP_NAME"),
								  rs.getString("HP_DNAME"),
								  rs.getString("HP_PHONE"),
								  rs.getString("HP_EMAIL"),
								  rs.getString("HP_LOC"),
								  rs.getString("HP_INTRO"),
								  rs.getString("HP_PHOTO"),
								  rs.getString("HP_START"),
								  rs.getString("HP_END"),
								  rs.getString("HP_LUNCH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return hp;
	}

	public ArrayList<String> searchId(Connection conn, String userName, String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> hpIdList = null;
		
		String query = prop.getProperty("searchId");
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, email);
			
			rs = pstmt.executeQuery();
			hpIdList = new ArrayList<String>();
			while(rs.next()) {
				hpIdList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return hpIdList;
	}

}
