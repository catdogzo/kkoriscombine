package calendar.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import calendar.model.dao.CalendarDAO;
import calendar.model.vo.Calendar;

public class CalendarService {

	public ArrayList<Calendar> selectCal(String usId) {
		Connection conn = getConnection();
		
		ArrayList<Calendar> result = new CalendarDAO().selectCal(conn, usId);
		close(conn);
		
		
		return result;
	}

}
