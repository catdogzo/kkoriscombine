package knBoard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import knBoard.model.dao.KnDAO;
import knBoard.model.vo.KnBoard;
import knBoard.model.vo.KnReply;
import photo.model.vo.Photo;

public class KnService {
	
	public int getListCount() {
		// 리스트 수 		
		Connection conn = getConnection();
		
		int result = new KnDAO().getListCount(conn);
		close(conn);
		return result;
	}

	public int getListCount(String searchCategory, String searchTag) {
		Connection conn = getConnection();
		
		int result = new KnDAO().getListCount(conn, searchCategory, searchTag);
		close(conn);
		return result;
	}
	
	public ArrayList<KnBoard> selectList(int currentPage) {
		// 리스트 불러오기		
		Connection conn = getConnection();
		ArrayList<KnBoard> list = new KnDAO().selectList(conn, currentPage);
		close(conn);
		
		return list;
	}
	
	
	public int insertKn(KnBoard kn, ArrayList<Photo> fileList) {
		// 게시글 작성		
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result1 = knd.insertKn(conn, kn);
		if(result1 > 0){
			
			int result2 = knd.insertPhoto(conn, fileList);
			if (result2 > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}else {
		
			rollback(conn);		
		}

		return result1;
	}
	


	public KnBoard selectKn(int knNum) {
		// 게시글 보기		
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.countKn(conn, knNum);
		KnBoard kn = null;
		
		if(result > 0) {
			kn = knd.selectKn(conn, knNum);
			
			if(kn != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
		} else {
			rollback(conn);
		}
		
		return kn;
	}

	public int updateKn(KnBoard kn) {
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		 
		int result = knd.updateKn(conn, kn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int updateKn(KnBoard kn, ArrayList<Photo> changeFile) {
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();

		int result1 = knd.updateKn(conn, kn);
		int result2 = knd.updatePhoto(conn, changeFile);
		System.out.println(result1);
		System.out.println(result2);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result1;

	}

	public int updateKn(KnBoard kn, ArrayList<Photo> changeFile, ArrayList<Photo> newInsertFile) {
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		int no = kn.getKnNum();
		
		int result1 = knd.updateKn(conn, kn);
		int result2 = knd.updatePhoto(conn, changeFile);
		int result3 = knd.insertNewPhoto(conn, newInsertFile, no);
		
		if(result1 > 0 && result2 > 0 && result3 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result1;
	}	
	
	
	public int deleteKn(int no) {
		// 게시글 삭제		
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.deleteKn(conn, no);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;		

	}


	public ArrayList<KnReply> insertKnr(KnReply knr, String usId) {
		// 댓글 달기		
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.insertKnr(conn, knr, usId);
		
		ArrayList<KnReply> list = null;
		if(result > 0) {
			commit(conn);
			list = knd.selectKnr(conn, knr.getKnNum());
		} else {
			rollback(conn);
		}
		
		return list;
	}
	

	public ArrayList<KnReply> selectKnr(int no) {
		// 댓글 선택		
		Connection conn = getConnection();		
		ArrayList<KnReply> list = new KnDAO().selectKnr(conn, no);
		return list;
	}	



	public ArrayList<Photo> selectPhoto(int no) {
		// 사진 불러오기
		Connection conn = getConnection();
		ArrayList<Photo> pList = new KnDAO().selectPhoto(conn, no);
		
		return pList;
	}

	public ArrayList<KnBoard> knSearchList(int currentPage, String searchCategory, String searchTag) {
		Connection conn = getConnection();
		ArrayList<KnBoard> list = new KnDAO().knSearchList(conn, currentPage, searchCategory, searchTag);
		close(conn);
		
		return list;
	}





	
}
