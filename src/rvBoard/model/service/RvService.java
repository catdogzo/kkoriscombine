package rvBoard.model.service;

import static common.CommonTemplate.close;
import static common.CommonTemplate.commit;
import static common.CommonTemplate.getConnection;
import static common.CommonTemplate.rollback;

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


	public int insertRv(RvBoard rv, ArrayList<Photo> fileList) {
		// 게시글 작성
		Connection conn = getConnection();
		RvDAO rvd = new RvDAO();
		int result1 = rvd.insertRv(conn, rv);
		
		if(result1 > 0) {
			int result2 = rvd.insertPhoto(conn, fileList);
			if (result2 > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}else {
			System.out.println(7);		
			rollback(conn);		
		}
		System.out.println(8);	
		return result1;	
	}
	

	public RvBoard selectRv(int no) {
		// 게시글 보기
		Connection conn = getConnection();
		RvDAO rvd = new RvDAO();
		RvBoard rv = null;
		
		rv = rvd.selectRv(conn, no);
		
		if(rv != null) {
			commit(conn);			
		} else {
			rollback(conn);
		}		
		return rv;
	}

	public ArrayList<Photo> selectPhoto(int no) {
		// 사진 불러오기
		Connection conn = getConnection();
		ArrayList<Photo> list = new RvDAO().selectPhoto(conn, no);		
		
		return list;
	}



	public int deleteRv(int no) {
		// 게시글 삭제
		Connection conn = getConnection();
		RvDAO rvd = new RvDAO();
		
		int result = rvd.deleteRv(conn, no);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}

	public int getListCount(String searchCategory, String searchTag) {
		Connection conn = getConnection();
		
		int result = new RvDAO().getListCount(conn, searchCategory, searchTag);
		close(conn);
		
		return result;
	}

	public ArrayList<RvBoard> RvSearchList(int currentPage, String searchCategory, String searchTag) {
		Connection conn = getConnection();
		ArrayList<RvBoard> list = new RvDAO().rvSearchList(conn, currentPage, searchCategory, searchTag);

		close(conn);
		
		return list;
	}

	public ArrayList<Photo> rvPsearch(ArrayList<RvBoard> list) {
		Connection conn = getConnection();
		ArrayList<Photo> pList = new RvDAO().rvPsearch(conn, list);
		
		close(conn);
		
		return pList;
	}

	public int insertLike(String writer, int no) {
		Connection conn = getConnection();
		
		int result = new RvDAO().insertLike(conn, writer, no);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}	
		close(conn);
		
		return result;
	}

	public int addLikeView(int no, int like) {
		Connection conn = getConnection();
		int result = new RvDAO().addLikeView(conn, no);
		int likeView = 0;
		if(result > 0) {
			commit(conn);
			likeView = new RvDAO().selectLike(conn, no);
		}else {
			rollback(conn);
		}	
		close(conn);
	
		return likeView;
	}		

}
