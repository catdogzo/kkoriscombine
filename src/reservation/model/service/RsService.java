package reservation.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import reservation.model.dao.RsDAO;
import reservation.model.vo.Reservation;

public class RsService {

	public ArrayList<Integer> searchRs(String hpId, String date) {
		Connection conn = getConnection();
		
		ArrayList<Integer> hours = new RsDAO().searchRs(conn, hpId, date);
		
		close(conn);
		return hours;
	}

}
