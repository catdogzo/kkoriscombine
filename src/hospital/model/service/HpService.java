package hospital.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import hospital.model.dao.HpDAO;
import hospital.model.vo.Hospital;
import hospital.model.vo.HpMedical;

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
	
	public ArrayList<Hospital> selectList(HashMap<String, String> dataMap) {
		Connection conn = getConnection();
		
		ArrayList<Hospital> list = new HpDAO().selectList(conn, dataMap);
		
		close(conn);
		return list;
	}

	public ArrayList<HpMedical> selectHm(String hpId) {
		Connection conn = getConnection();
		
		ArrayList<HpMedical> list = new HpDAO().selectHm(conn, hpId);
		
		close(conn);
		return list;
	}

	public HpMedical searchFee(String hpId, String cate) {
		Connection conn = getConnection();
		
		HpMedical hm = new HpDAO().searchFee(conn, hpId, cate);
		
		close(conn);
		return hm;
	}

}
