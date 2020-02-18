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

}
