package point.model.service;
import static common.CommonTemplate.*;
import static common.CommonTemplate.close;
import static common.CommonTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import point.model.dao.PtDAO;
import point.model.vo.Point;

public class PtService {

	public int getListCount(String usId) {
		Connection conn = getConnection();

		int result = new PtDAO().getListCount(conn, usId);
		close(conn);
		
		return result;
	}

	public ArrayList<Point> selectList(int currentPage, String usId) {
		Connection conn = getConnection();
		ArrayList<Point> list = new PtDAO().selectList(conn, currentPage, usId);
		close(conn);
		
		return list;
	}

	public int ptCoupon(String ptcName) {
		Connection conn = getConnection();
		int result = new PtDAO().ptCoupon(conn, ptcName);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

}
