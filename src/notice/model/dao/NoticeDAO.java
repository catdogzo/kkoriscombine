package notice.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import notice.model.vo.Notice;

import static common.JDBCTemplate.*;

public class NoticeDAO {
	private Properties prop = new Properties();
	
	public NoticeDAO() {
		String fileName = NoticeDAO.class.getResource("/sql/notice/notice-query.properties").getPath();
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
		ResultSet rs = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return result;
	}
	
	public int getListCount(Connection conn, String searchCategory, String searchTag) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String query = "";
		switch(searchCategory) {
			case "제목": query = prop.getProperty("getSLCTitle"); break;
			case "내용": query = prop.getProperty("getSLCContent"); break;
			case "제목+내용": query = prop.getProperty("getSLCTC"); break;
		}
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchTag);
			if(searchCategory.equals("제목+내용")) {
				pstmt.setString(2, searchTag);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

	public ArrayList<Notice> selectList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = prop.getProperty("selectList");
		ArrayList<Notice> list = null;
		
		int posts = 15;
		int startRow = (currentPage -1) * posts + 1;
		int endRow = startRow + posts-1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rs.next()) {
				Notice no = new Notice(rs.getInt("rnum2"),
									   rs.getInt("no_num"),
									   rs.getString("no_title"),
									   rs.getDate("no_date"),
									   rs.getString("no_con"),
									   rs.getString("au_id"));
				list.add(no);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Notice> noticeSearchList(Connection conn,int currentPage, String searchCategory, String searchTag) {
		PreparedStatement pstmt = null;
		ArrayList<Notice> list = new ArrayList<>();
		ResultSet rs = null;
		Notice n = null;
		
		int posts = 15;
		int startRow = (currentPage -1) * posts + 1;
		int endRow = startRow + posts-1;
		
		String query = prop.getProperty("searchList");
		switch(searchCategory) {
		case "제목": query = prop.getProperty("searchListTitle"); break;
		case "내용": query = prop.getProperty("searchListContent"); break;
		case "제목+내용": query = prop.getProperty("searchListTC"); break;
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchTag);
			if(searchCategory.equals("제목+내용")) {
				pstmt.setString(2, searchTag);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			} else {
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				n = new Notice(rs.getInt("rnum2"),
						   rs.getInt("no_num"),
						   rs.getString("no_title"),
						   rs.getDate("no_date"),
						   rs.getString("no_con"),
						   rs.getString("au_id"));
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int noticeInsert(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("noticeInsert");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoTitle());
			pstmt.setString(2, n.getNoCon());
			pstmt.setString(3, n.getAuId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Notice selectNotice(Connection conn, int noNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice n = null;
		String query = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String noTitle = rs.getString("no_title");
				Date noDate = rs.getDate("no_date");
				String noCon = rs.getString("no_con");
				String auId = rs.getString("au_id");
				n = new Notice(noNum, noTitle, noDate, noCon, auId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return n;
	}

	public int noticeUpdate(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("noticeUpdate");
		//UPDATE NOTICE SET NO_TITLE=?, NO_DATE=SYSDATE, NO_CON=? WHERE NO_NUM=?
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, n.getNoTitle());
			pstmt.setString(2, n.getNoCon());
			pstmt.setInt(3, n.getNoNum());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int noticeDelete(Connection conn, int noNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("noticeDelete");
		//UPDATE NOTICE SET NO_DEL='Y' WHERE NO_NUM=?
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
