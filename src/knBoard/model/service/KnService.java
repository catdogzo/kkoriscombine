package knBoard.model.service;

import static common.CommonTemplate.close;
import static common.CommonTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import knBoard.model.dao.KnDAO;
import knBoard.model.vo.KnBoard;

public class KnService {
	
	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new KnDAO().getListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<KnBoard> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<KnBoard> list = new KnDAO().selectList(conn, currentPage);
		close(conn);
		
		return list;
	}	
	
}
