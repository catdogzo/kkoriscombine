package reservation.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import reservation.model.vo.Reservation;

public class RsDAO {
	private Properties prop = new Properties();
	
	public RsDAO() {
		String fileName = RsDAO.class.getResource("/sql/reservation/reservation-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> searchRs(Connection conn, String hpId, String date) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> hours = null;
		
		String query = prop.getProperty("searchRs");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, hpId);
			pstmt.setString(2, date);
			
			rs = pstmt.executeQuery();
			hours = new ArrayList<Integer>();
			while(rs.next()) {
				hours.add(rs.getTimestamp("RS_DATE").getHours());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return hours;
	}
	
	
}
