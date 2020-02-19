package knBoard.model.service;

import static common.CommonTemplate.close;
import static common.CommonTemplate.commit;
import static common.CommonTemplate.getConnection;
import static common.CommonTemplate.rollback;

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

	public ArrayList<KnBoard> selectList(int currentPage) {
		// 리스트 불러오기		
		Connection conn = getConnection();
		ArrayList<KnBoard> list = new KnDAO().selectList(conn, currentPage);
		close(conn);
		
		return list;
	}
	
	
	public int insertKn(KnBoard kn) {
		// 게시글 작성		
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.insertKn(conn, kn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}		
		return result;
	}		

	public int updateKn(KnBoard kn) {
		// 게시글 수정		
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.updateKn(conn, kn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);	
		
		return result;
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


	public ArrayList<KnReply> insertKnr(KnReply knr) {
		// 댓글 달기		
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.insertKnr(conn, knr);
		
		ArrayList<KnReply> list = null;
		if(result > 0) {
			commit(conn);
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

	
	public int insertPhoto(int bNum, ArrayList<Photo> fileList) {
		// 사진 올리기
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();

		int result = knd.insertPhoto(conn, bNum, fileList);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);		
		}

		return result;
	}

	public int updatePhoto(ArrayList<Photo> fileList) {
		//사진 수정
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.updatePhoto(conn, fileList);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}

	public ArrayList<Photo> selectPhoto(int no) {
		// 사진 불러오기
		Connection conn = getConnection();
		ArrayList<Photo> list = new KnDAO().selectPhoto(conn, no);
		
		return list;
	}

	public int insertLike(String usId) {
		Connection conn = getConnection();
		int result = new KnDAO().insertLike(conn, usId);
		
		return 0;
	}	
	
}
