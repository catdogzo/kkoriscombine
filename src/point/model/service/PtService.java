package point.model.service;
import static common.CommonTemplate.close;
import static common.CommonTemplate.commit;
import static common.CommonTemplate.getConnection;
import static common.CommonTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import point.model.dao.PtDAO;
import point.model.vo.Point;
import point.model.vo.PtUse;

public class PtService {

	public int getListCount(String usId) {
		Connection conn = getConnection();

		int result = new PtDAO().getListCount(conn, usId);
		close(conn);
		
		return result;
	}

	public ArrayList<Point> selectList(String usId) {
		Connection conn = getConnection();
		ArrayList<Point> list = new PtDAO().selectList(conn, usId);
		close(conn);
		
		return list;
	}

	public int ptCoupon(String ptcName, String usId) {
		Connection conn = getConnection();
		int result = new PtDAO().ptCoupon(conn, ptcName, usId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<PtUse> selCoupon(String usId) {
		Connection conn = getConnection();
		ArrayList<PtUse> list = new PtDAO().selCoupon(conn, usId);
		
		return list;
	}

	public int couponVal(String coupon) {
		Connection conn = getConnection();
		int result = new PtDAO().couponVal(conn, coupon);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}

}
