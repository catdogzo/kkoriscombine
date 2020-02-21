package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;

import static common.JDBCTemplate.*;

public class NoticeService {

	public int getListCount() {
		Connection conn = getConnection();
		int result = new NoticeDAO().getListCount(conn);
		close(conn);
		return result;
	}
	public int getListCount(String searchCategory, String searchTag) {
		Connection conn = getConnection();
		int result = new NoticeDAO().getListCount(conn, searchCategory, searchTag);
		return result;
	}

	public ArrayList<Notice> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDAO().selectList(conn, currentPage);
		close(conn);
		return list;
	}
	
	public ArrayList<Notice> noticeSearchList(int currentPage, String searchCategory, String searchTag) {
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDAO().noticeSearchList(conn, currentPage, searchCategory, searchTag);
		close(conn);
		return list;
	}

	public int noticeInsert(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDAO().noticeInsert(conn, n);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Notice selectNotice(int noNum) {
		Connection conn = getConnection();
		Notice n = new NoticeDAO().selectNotice(conn, noNum);
		
		close(conn);
		return n;
	}

	public int noticeUpdate(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDAO().noticeUpdate(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int noticeDelete(int noNum) {
		Connection conn = getConnection();
		int result = new NoticeDAO().noticeDelete(conn, noNum);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	

}
