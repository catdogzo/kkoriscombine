package hospital.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;

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

	public String searchId(String userName, String email) {
		Connection conn = getConnection();
		
		String hpId = new HpDAO().searchId(conn, userName, email);
		
		close(conn);
		return hpId;
	}

}
