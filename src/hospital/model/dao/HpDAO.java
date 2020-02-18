package hospital.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			pstmt.setString(5, hp.getHpLoc());
			pstmt.setString(6, hp.getHpStart());
			pstmt.setString(7, hp.getHpEnd());
			pstmt.setString(8, hp.getHpLunch());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
