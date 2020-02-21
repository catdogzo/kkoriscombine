package calendar.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import calendar.model.vo.Calendar;

public class CalendarDAO {


	public ArrayList<Calendar> selectCal(Connection conn, String usId) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Calendar> result = null;
		
		try {
			pstmt = conn.prepareStatement("selectCal");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
