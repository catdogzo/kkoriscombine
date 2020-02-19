package rvBoard.model.dao;

import static common.CommonTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
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

}
