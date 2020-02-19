package rvBoard.model.service;

import static common.CommonTemplate.close;
import static common.CommonTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import photo.model.vo.Photo;
import rvBoard.model.dao.RvDAO;
import rvBoard.model.vo.RvBoard;

public class RvService {
	public int getListCount() {
		// 리스트 수
		Connection conn = getConnection();
		int result = new RvDAO().getListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<RvBoard> selectList(int currentPage) {
		// 리스트 
		Connection conn = getConnection();
		ArrayList<RvBoard> rlist = new RvDAO().selectRList(conn, currentPage);
		close(conn);
		
		return rlist;
	}

	public ArrayList<Photo> selectPlist(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Photo> plist = new RvDAO().selectPList(conn, currentPage);
				
		return plist;
	}
}
