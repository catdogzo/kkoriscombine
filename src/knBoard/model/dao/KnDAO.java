package knBoard.model.dao;

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

		public int getListCount(Connection conn, String searchCategory, String searchTag) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;

			String query = "";		
			switch(searchCategory) {
			case "작성자": query = prop.getProperty("getSLCWrt"); break;
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
		
		public ArrayList<KnBoard> selectList(Connection conn, int currentPage) {
			// list 불러오기
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<KnBoard> list = null;
			int posts = 15;			
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
					kn = new KnBoard(rset.getInt("kn_num"),
									rset.getString("kn_title"),
									rset.getString("kn_con"),
									rset.getString("us_nick"),
									rset.getInt("kn_view"),
									rset.getDate("kn_date"));
				}
							
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
	
			return kn;
		}

		public int insertKn(Connection conn, KnBoard kn) {
			//
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("insertKn");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, kn.getknTitle());
				pstmt.setString(2, kn.getKnCon());
				pstmt.setString(3, kn.getUsNick());
		
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
				pstmt.setString(1, knr.getKnrCon());
				pstmt.setString(2, knr.getUsId());
				pstmt.setInt(3, knr.getKnNum());
				
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
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				
				list = new ArrayList<KnReply>();
				while(rset.next()) {
					list.add(new KnReply(rset.getInt("knr_num"),
										rset.getString("knr_con"),
										rset.getDate("knr_date"),
										rset.getInt("kn_num"),
										rset.getString("us_nick")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}

		public int insertPhoto(Connection conn, ArrayList<Photo> fileList) {
			PreparedStatement pstmt = null;
			int result = 0;
			Photo p = null;
			String query = prop.getProperty("insertKnPhoto");
			
			try {
				for(int i = 0; i < fileList.size(); i++) {
					p = fileList.get(i);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, p.getPhOrig());
					pstmt.setString(2, p.getPhChng());
					pstmt.setString(3, p.getPhPath());
					pstmt.setInt(4, p.getPhFnum());
			
					result += pstmt.executeUpdate();
				}
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
				pstmt.setString(1, kn.getknTitle());
				pstmt.setString(2, kn.getKnCon());
				pstmt.setInt(3, kn.getKnNum());
				
				result = pstmt.executeUpdate();
				
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
			Photo p = null;
			String query = prop.getProperty("updatePhoto");
			
			try {
				for(int i = 0; i < fileList.size(); i++) {
					p = fileList.get(i);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, p.getPhOrig());
					pstmt.setString(2, p.getPhChng());
					pstmt.setInt(3, p.getPhNum());
					
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
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Photo> pList = null;
			String query = prop.getProperty("selectKnPhoto");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				pList = new ArrayList<Photo>();
				if(rset != null) {
					while(rset.next()) {
						Photo p = new Photo();
						p.setPhNum(rset.getInt("ph_num"));
						p.setPhFnum(rset.getInt("ph_fnum"));
						p.setPhOrig(rset.getString("ph_orig"));
						p.setPhChng(rset.getString("ph_chng"));
						p.setPhPath(rset.getString("ph_path"));
						p.setPhUpload(rset.getDate("ph_upload"));
						
						pList.add(p);
					}
				}		
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
	
			return pList;
		}

		public int insertNewPhoto(Connection conn, ArrayList<Photo> newInsertFile) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("insertNewPhoto");
			
			try {
				for(int i = 0; i < newInsertFile.size(); i++) {
					Photo p = newInsertFile.get(i);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, p.getPhOrig());
					pstmt.setString(2, p.getPhChng());
					pstmt.setString(3, p.getPhPath());
					pstmt.setInt(4, p.getPhFnum());
					pstmt.setInt(5, p.getKnNum());

				}	
					result += pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		public int insertLike(Connection conn, String usId) {
			// 좋아요 포인트 추가
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("insertLike");
			
			try {
				pstmt = conn.prepareStatement(query);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public ArrayList<KnBoard> knSearchList(Connection conn, int currentPage, String searchCategory, String searchTag) {
			PreparedStatement pstmt = null;
			ArrayList<KnBoard> list = new ArrayList<>();
			ResultSet rset = null;
			KnBoard kn = null;
			
			int posts = 15;
			int startRow = (currentPage -1) * posts + 1;
			int endRow = startRow + posts-1;
			
			String query = prop.getProperty("knsearchList");			
			switch(searchCategory) {
			case "작성자": query = prop.getProperty("searchwrt"); break;
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
					kn = new KnBoard(
							   rset.getInt("kn_num"),
							   rset.getString("kn_title"),
							   rset.getDate("kn_date"),
							   rset.getString("kn_con"),
							   rset.getString("us_nick"));
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


		
		
}
