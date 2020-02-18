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
import knBoard.model.vo.KnReply;
import photo.model.vo.Photo;

public class KnDAO {

		private Properties prop = new Properties();
		
		public KnDAO() {
			// prop 파일 import
			String fileName = KnDAO.class.getResource("/sql/Knboard/knb-query.properties").getPath();			
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
			// list 불러오기
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
					KnBoard kn = new KnBoard(rset.getInt("kn_num"),
										rset.getString("kn_title"),
										rset.getString("kn_con"),
										rset.getString("us_nick"),
										rset.getInt("kn_view"),
										rset.getDate("kn_date"),
										rset.getDate("kn_update"),
										rset.getString("kn_del"));
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

		public int countKn(Connection conn, int knNum) {
			// 조회수 카운트
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("countKn");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, knNum);
				
				result = pstmt.executeUpdate();
						
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		
			return result;
		}

		public KnBoard selectKn(Connection conn, int knNum) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			KnBoard kn = null;
			String query = prop.getProperty("selectKn");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, knNum);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					kn = new KnBoard(rset.getInt("knNum"));
				}
							
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
	
			return kn;
		}

		public int insertKn(Connection conn, KnDAO knd) {
			//
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("insertKn");
			
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

		public int updateKn(Connection conn, KnBoard kn) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("updateKn");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, x);
				pstmt.setString(parameterIndex, x);
				pstmt.setString(parameterIndex, x);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public int deleteKn(Connection conn, int no) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("deleteKn");
			
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

		public int insertKnr(Connection conn, KnReply knr) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("inserKnr");
		
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

		public ArrayList<KnReply> selectKnr(Connection conn, int no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<KnReply> list = null;
			String query = prop.getProperty("selectKnr");
			
			try {
				pstmt = conn.prepareStatement("selectKnr");
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				
				list = new ArrayList<KnReply>();
				while(rset.next()) {
					list.add(new KnReply(rset.getInt("")))
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}

		public int insertPhoto(Connection conn, int bNum, ArrayList<Photo> fileList) {
			PreparedStatement pstmt = null;
			int result = 0;
			Photo p = null;
			String query = prop.getProperty("insertPhoto");
			
			try {
				for(int i = 0; i < fileList.size(); i++) {
					p = fileList.get(i);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, x);
					
					result += pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}			
			return result;
		}

		public int updatePhoto(Connection conn, ArrayList<Photo> fileList) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("updatePhoto");
			
			try {
				for(int i = 0; i < fileList.size(); i++) {
					p = fileList.get(i);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, x);
					
					result += pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}			
			return result;
		}
		
		
		
}
