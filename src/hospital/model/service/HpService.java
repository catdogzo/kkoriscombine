package hospital.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import hospital.model.dao.HpDAO;
import hospital.model.vo.Hospital;

public class HpService {

	public int joinHp(Hospital hp) {
		Connection conn = getConnection();
		
		int result = new HpDAO().joinHp(conn, hp);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Hospital selectHp(String auId) {
		Connection conn = getConnection();
		
		Hospital hp = new HpDAO().selectHp(conn, auId);
		
		close(conn);
		return hp;
	}

	public ArrayList<String> searchId(String userName, String email) {
		Connection conn = getConnection();
		
		ArrayList<String> hpIdList = new HpDAO().searchId(conn, userName, email);
		
		close(conn);
		return hpIdList;
	}

}
