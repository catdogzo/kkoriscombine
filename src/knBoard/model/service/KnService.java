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
	
	
	public int insertKn(KnBoard kn) {
		Connection conn = getConnection();
		KnDAO knd = new KnDAO();
		
		int result = knd.insertKn(conn, knd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}		
		return result;
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
		
		close(conn);	
		
		return result;
	}

	public KnBoard selectKn(int knNum) {
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
		Connection conn = getConnection();		
		ArrayList<KnReply> list = new KnDAO().selectKnr(conn, no);

		return list;
	}	

	
	public int insertPhoto(int bNum, ArrayList<Photo> fileList) {
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
	
}
