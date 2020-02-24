package point.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import point.model.vo.Point;
import point.model.vo.PtUse;

public class PtDAO {
	
	private Properties prop = new Properties();
	
	public PtDAO() {
		// prop 파일 import
		String fileName = PtDAO.class.getResource("/sql/Point/pt-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int getListCount(Connection conn, String usId) {
		// list 수 불러오기
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, usId);
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

	public ArrayList<Point> selectList(Connection conn, int currentPage, String usId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Point> list = null;
		int posts = 10; // 한 페이지에 보여질 게시글 개수
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, usId);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Point>();
			
			while(rset.next()) {
				Point pt = new Point(rset.getInt("pt_num"),
							rset.getInt("pt_use"),
							rset.getInt("pt_add"),
							rset.getInt("pt_cate"),
							rset.getDate("pt_date"),
							rset.getString("us_id"));
				
				list.add(pt);		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int ptCoupon(Connection conn, String ptcName, String usId) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String query = prop.getProperty("ptCoupon");
		String query2 = prop.getProperty("buyCoupon");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt2 = conn.prepareStatement(query2);
			pstmt.setString(1, ptcName);
			pstmt.setString(2, usId);
			pstmt2.setString(1, usId);
			pstmt.executeUpdate();
			result = pstmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public ArrayList<PtUse> selCoupon(Connection conn, String usId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selCoupon");
		ArrayList<PtUse> ptu = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, usId);
			rset = pstmt.executeQuery();
			ptu = new ArrayList<PtUse>();
			
			while(rset.next()) {
				PtUse p = new PtUse(rset.getString("ptc_serial"),
								rset.getString("ptc_cname"),
								rset.getString("ptc_use"));
				ptu.add(p);
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ptu;
	}

}
