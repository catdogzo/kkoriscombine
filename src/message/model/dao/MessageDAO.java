package message.model.dao;

import static common.JDBCTemplate.close;

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

import message.model.vo.Message;

public class MessageDAO {
	private Properties prop = new Properties();
	
	public MessageDAO () {
		System.out.println("에러니4-1");
		String fileName = MessageDAO.class.getResource("/sql/message/message.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("에러니?4");
 		
	}

	public int getListCount(Connection conn) {
		System.out.println("에러니?5-1");
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			System.out.println("에러니?5");

			if(rset.next()) {
				result = rset.getInt(1);
				System.out.println("에러니?6");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		

		System.out.println(result);
		return result;
	}

	public ArrayList<Message> selectList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Message> mList = null;
		System.out.println("에러니?7");

		int posts = 10; 
		int startRow = (currentPage -1) * posts + 1; 
		int endRow = startRow + posts -1;
		
		String query = prop.getProperty("selectMlist");
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			mList = new ArrayList<Message>();
			while(rs.next()) {
				Message m = new Message(rs.getInt("msg_num"),
										rs.getString("msg_title"),
										rs.getDate("msg_date"),
										rs.getString("msg_con"),
										rs.getString("rsg_id"),
										rs.getString("ssg_id"),
										rs.getString("ssgDel"),
										rs.getString("rsgDel"),
										rs.getString("status"));
				mList.add(m);
				System.out.println("에러니?8");

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return mList;
	}

}
