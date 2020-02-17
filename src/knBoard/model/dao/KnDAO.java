package knBoard.model.dao;

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

import knBoard.model.vo.KnBoard;
import common.model.vo.Photo;

public class KnDAO {

		private Properties prop = new Properties();
		
		public KnDAO() {
			// prop 파일 import
			String fileName = KnDAO.class.getResource("/sql/knBoard/board-query.properties").getPath();			
			try {
				prop.load(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}

		public int getListCount(Connection conn) {
			// list 수 불러오기
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

		public ArrayList<KnBoard> selectList(Connection conn, int currentPage) {
			// 리스트 선택
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<KnBoard> list = null;
			int posts = 10;			
			int startRow = (currentPage - 1) * posts + 1;
			int endRow = startRow + posts - 1;
			
			String query = prop.getProperty("selectList");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				list = new ArrayList<KnBoard>();
				
				while(rset.next()) {
					KnBoard kn = new KnBoard(rset.getInt("bid"),
										rset.getInt("btype"),
										rset.getString("cname"),
										rset.getString("btitle"),
										rset.getString("bcontent"),
										rset.getString("nickName"),
										rset.getInt("bcount"),
										rset.getDate("create_date"),
										rset.getDate("modify_date"),
										rset.getString("status"));
					list.add(kn);					
				}
	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}				
			return list;
		}

		public int insertBoard(Connection conn, KnDAO knd) {
			//
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("insertBoard");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, cate);
				pstmt.setString(2, knd.getbTitle());
				pstmt.setString(3, knd.getbContent());
				pstmt.setString(4, knd.getbWriter());
		
				result = pstmt.executeUpdate();
	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
	
			return result;
		}

		public int insertPhoto(Connection conn, ArrayList<Photo> fileList) {
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = prop.getProperty("insertPhoto");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, x);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		
		
		
}
