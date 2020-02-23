package rvBoard.model.dao;
import static common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import photo.model.vo.Photo;
import rvBoard.model.vo.RvBoard;

public class RvDAO {
	
	private Properties prop = new Properties();
	
	public RvDAO() {
		// prop 파일 import
		String fileName = RvDAO.class.getResource("/sql/RvBoard/rvb-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
	
		return result;
	}

	public ArrayList<RvBoard> selectRList(Connection conn, int currentPage) {
		// list 불러오기
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RvBoard> rList = null;
		
		int posts = 15;			
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
						
		String query = prop.getProperty("selectRList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			rList = new ArrayList<RvBoard>();

			while(rset.next()) {
				rList.add(new RvBoard(rset.getInt("rv_num"),
									  rset.getString("rv_title"),
									  rset.getDate("rv_date"),
									  rset.getString("rv_con"),
									  rset.getString("hp_name"),
									  rset.getString("us_id"),
									  rset.getInt("rv_star")));
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return rList;
	}

	public ArrayList<Photo> selectPList(Connection conn, int currentPage) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Photo> plist = null;
		String query = prop.getProperty("selectPList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			plist = new ArrayList<Photo>();
			
			while(rset.next()) {
					plist.add(new Photo(rset.getInt("rv_num"),
							rset.getString("ph_chng")));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return plist;
	}

	public int insertRv(Connection conn, RvBoard rv) {
		// 글 작성
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int result = 0;
		String query = prop.getProperty("insertRv");
		String query2 = prop.getProperty("insertPt");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rv.getRvTitle());
			pstmt.setInt(2, rv.getRvStar());
			pstmt.setDate(3, rv.getRvDate());
			pstmt.setString(4, rv.getRvCon());
			pstmt.setString(5, rv.getHpId());
			pstmt.setString(6, rv.getUsId());		
			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, rv.getUsId());			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(pstmt2);
		}
		return result;
	}

	public RvBoard selectRv(Connection conn, int no) {
		// 리뷰글 선택
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RvBoard rv =  null;
		String query = prop.getProperty("selectRv");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				rv = new RvBoard(rset.getInt("rv_num"),
								rset.getString("rv_title"),
								rset.getDate("rv_date"),
								rset.getInt("rv_star"),
								rset.getString("rv_con"),
								rset.getString("hp_name"),
								rset.getString("us_id"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return rv;
	}

	public int updateRv(Connection conn, RvBoard rv) {
		// 리뷰 게시판 수정
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateRv");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rv.getRvTitle());
			pstmt.setString(2, rv.getRvCon());
			pstmt.setInt(3, rv.getRvNum());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertPhoto(Connection conn, ArrayList<Photo> fileList) {
		// 사진 첨부
		PreparedStatement pstmt = null;
		int result = 0;
		Photo p = null;
		String query = prop.getProperty("insertRvPhoto");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				p = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, p.getPhOrig());
				pstmt.setString(2, p.getPhChng());
				pstmt.setString(3, p.getPhPath());
				pstmt.setInt(4, p.getPhFnum());
				pstmt.setInt(5, p.getKnNum());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Photo> selectPhoto(Connection conn, int no) {
		// 사진 불러오기
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Photo> pList = null;
		String query = prop.getProperty("selectRvPhoto");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			pList = new ArrayList<Photo>();
			
			while(rset.next()) {
				Photo p = new Photo();
				p.setPhFnum(rset.getInt("ph_fnum"));
				p.setPhOrig(rset.getString("ph_orig"));
				p.setPhChng(rset.getString("ph_chng"));
				p.setPhPath(rset.getString("ph_path"));
				p.setPhUpload(rset.getDate("ph_upload"));
				
				pList.add(p);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return pList;
	}
	
	public int updatePhoto(Connection conn, ArrayList<Photo> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		Photo p = null;
		String query = prop.getProperty("updatePhoto");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				p = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, p.getPhOrig());
				pstmt.setString(2, p.getPhChng());
				pstmt.setString(3, p.getPhPath());
				pstmt.setInt(4, p.getPhNum());
				
				result += pstmt.executeUpdate();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteRv(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteRv");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getListCount(Connection conn, String searchCategory, String searchTag) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = "";		
		switch(searchCategory) {
		case "병원명": query = prop.getProperty("getSLCHp"); break;
		case "제목": query = prop.getProperty("getSLCTitle"); break;
		case "내용": query = prop.getProperty("getSLCCon"); break;
		}	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchTag);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<RvBoard> rvSearchList(Connection conn, int currentPage, String searchCategory, String searchTag) {
		PreparedStatement pstmt = null;
		ArrayList<RvBoard> list = new ArrayList<>();
		ResultSet rset = null;
		RvBoard rv = null;

		int posts = 15;
		int startRow = (currentPage -1) * posts + 1;
		int endRow = startRow + posts-1;
		
		String query = prop.getProperty("rvsearchList");			
		switch(searchCategory) {
		case "병원명": query = prop.getProperty("searchHp"); break;
		case "제목": query = prop.getProperty("searchTitle"); break;
		case "내용": query = prop.getProperty("searchContent"); break;
		}	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchTag);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);				

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				rv = new RvBoard(rset.getString("hp_name"),
								rset.getInt("rv_num"),
								rset.getString("rv_title"));
					}
			list.add(rv);
				
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return list;
	}

	public ArrayList<Photo> rvPsearch(Connection conn, ArrayList<RvBoard> list) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Photo> pList = null;
		String query = prop.getProperty("rvPsearch");
		
		try {
			pstmt = conn.prepareStatement(query);
			for(int i = 0; i < list.size(); i++) {
				pstmt.setInt(1, list.get(i).getRvNum());			
			}
			rset = pstmt.executeQuery();
			pList = new ArrayList<Photo>();
			while (rset.next()) {
				pList.add(new Photo(rset.getInt("rv_num"),
												rset.getString("ph_chng")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return pList;
	}

}
