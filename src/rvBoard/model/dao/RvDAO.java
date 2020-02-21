package rvBoard.model.dao;

import static common.CommonTemplate.close;

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
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<RvBoard> rList = null;
		
		String query = prop.getProperty("selectRlist");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				rList.add(new RvBoard(rset.getInt("rv_num"),
									  rset.getString("rv_title"),
									  rset.getDate("rv_date"),
									  rset.getInt("rv_str"),
									  rset.getString("rv_con"),
									  rset.getString("hp_name"),
									  rset.getString("us_nick")));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
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
			
			while(rset.next()) {
				if(rset.getString("ph_chng") == null) {
					plist.add(new Photo(0, "default.png"));
				}else {
					plist.add(new Photo(rset.getInt("rv_num"),
							rset.getString("ph_chng")));
				}
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
		int result = 0;
		String query = prop.getProperty("insertRv");
		String query2 = prop.getProperty("insertPt");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rv.getRvTitle());
			pstmt.setInt(2, rv.getRvStar());
			pstmt.setString(3, rv.getRvCon());
			pstmt.setString(4, rv.getHpId());
			pstmt.setString(5, rv.getUsId());
			
			pstmt = conn.prepareStatement(query2);
			pstmt.setString(1, rv.getUsId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
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
		ArrayList<Photo> list = null;
		String query = prop.getProperty("selectRvPhoto");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			list = new ArrayList<Photo>();
			
			while(rset.next()) {
				Photo p = new Photo();
				p.setPhFnum(rset.getInt("ph_fnum"));
				p.setPhOrig(rset.getString("ph_orig"));
				p.setPhChng(rset.getString("ph_chng"));
				p.setPhPath(rset.getString("ph_path"));
				p.setPhUpload(rset.getDate("ph_upload"));
				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return list;
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
			pstmt = conn.prepareStatement("deleteRv");
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
