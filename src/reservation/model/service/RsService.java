package reservation.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import reservation.model.dao.RsDAO;
import reservation.model.vo.Reservation;
import reservation.model.vo.ReservationInfo;

public class RsService {

	public ArrayList<Integer> searchRs(String hpId, String date) {
		Connection conn = getConnection();
		
		ArrayList<Integer> hours = new RsDAO().searchRs(conn, hpId, date);
		
		close(conn);
		return hours;
	}

	public Reservation insertRs(Reservation rs) {
		Connection conn = getConnection();
		RsDAO dao = new RsDAO();
		Reservation reservation = null;
		
		int result= dao.insertRs(conn, rs);
		if(result > 0) {
			commit(conn);
			reservation = dao.currentRs(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return reservation;
	}

	public Reservation selectRs(int rsNum) {
		Connection conn = getConnection();
		Reservation reservation = new RsDAO().selectRs(conn, rsNum);
		
		close(conn);
		return reservation;
	}

	public int deleteRs(int rsNum) {
		Connection conn = getConnection();
		int result = new RsDAO().deleteRs(conn, rsNum);
		close(conn);
		return result;
	}

	public ArrayList<ReservationInfo> listRs(String usId) {
		Connection conn = getConnection();
		ArrayList<ReservationInfo> riList = new RsDAO().listRs(conn, usId);
		close(conn);
		return riList;
	}

}
