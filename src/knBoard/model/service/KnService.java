package knBoard.model.service;

import static common.CommonTemplate.*;


import java.sql.Connection;
import java.util.ArrayList;

import knBoard.model.dao.KnDAO;
import knBoard.model.vo.KnBoard;
import common.model.vo.Photo;

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
	
	
	public int insertBoard(KnBoard kn) {
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.insertBoard(conn, knd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}		
		return result;
	}		

	public int insertPhoto(ArrayList<Photo> fileList) {
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		int result =  knd.insertPhoto(conn, fileList);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);		
		}

		return 0;
	}


	
}
