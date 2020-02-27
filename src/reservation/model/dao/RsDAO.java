package reservation.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import reservation.model.vo.Reservation;
import reservation.model.vo.ReservationInfo;

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

	public int insertRs(Connection conn, Reservation rs) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertRs");
		try {
			pstmt = conn.prepareStatement(query);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String rsDate = sdf.format(rs.getRsDate()); // 타임스탬프 포맷에 맞게 String화 시키기
			pstmt.setString(1, rsDate);
			pstmt.setString(2, rs.getRsMemo());
			pstmt.setInt(3, rs.getPetNum());
			pstmt.setString(4, rs.getHmCate());
			pstmt.setString(5, rs.getHpId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ReservationInfo currentRs(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		ReservationInfo ri = null;
		
		String query = prop.getProperty("currentRs");

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				ri = new ReservationInfo(rs.getInt("RS_NUM"),
											  rs.getString("HP_NAME"),
											  rs.getTimestamp("RS_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return ri;
	}

	public Reservation selectRs(Connection conn, int rsNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Reservation reservation = null;
		
		String query = prop.getProperty("selectRs");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rsNum);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reservation = new Reservation(rs.getInt("RS_NUM"),
											  rs.getTimestamp("RS_DATE"),
											  rs.getString("RS_MEMO"),
											  rs.getInt("PET_NUM"),
											  rs.getString("HM_CATE"),
											  rs.getString("HP_ID"),
											  rs.getString("RS_VISIT"),
											  rs.getString("RS_DEL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reservation;
	}

	public int deleteRs(Connection conn, int rsNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteRs");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rsNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<ReservationInfo> listRs(Connection conn, String usId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReservationInfo> riList = null;
		
		String query = prop.getProperty("listRs"); // ORDER BY DESC
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, usId);
			
			rs = pstmt.executeQuery();
			riList = new ArrayList<ReservationInfo>();
			while(rs.next()) {
				riList.add(new ReservationInfo(rs.getInt("RS_NUM"),
											   rs.getString("HP_NAME"),
											   rs.getTimestamp("RS_DATE"),
											   rs.getString("HM_CATE"),
											   rs.getString("PET_NAME"),
											   rs.getString("RS_VISIT"),
											   rs.getString("RS_DEL")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return riList;
	}

	public int updateRs(Connection conn, Reservation rs) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateRs");
		try {
			pstmt = conn.prepareStatement(query);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String rsDate = sdf.format(rs.getRsDate()); // 타임스탬프 포맷에 맞게 String화 시키기
			
			pstmt.setString(1, rsDate);
			pstmt.setString(2, rs.getRsMemo());
			pstmt.setInt(3, rs.getPetNum());
			pstmt.setString(4, rs.getHmCate());
			pstmt.setInt(5, rs.getRsNum());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
}
