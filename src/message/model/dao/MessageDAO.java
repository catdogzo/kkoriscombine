package message.model.dao;

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

import message.model.vo.Message;

public class MessageDAO {
	private Properties prop = new Properties();
	
	public MessageDAO () {
		String fileName = MessageDAO.class.getResource("/sql/message/message.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
 		
	}

	public int getListCount(Connection conn, String rsgId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rsgId);
			
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

	public ArrayList<Message> selectList(Connection conn, int currentPage, String rsgId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Message> mList = null;

		int posts = 10; 
		int startRow = (currentPage -1) * posts + 1; 
		int endRow = startRow + posts -1;
		
		String query = prop.getProperty("selectMlist");
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, rsgId);
			rs = pstmt.executeQuery();
			mList = new ArrayList<Message>();
			while(rs.next()) {
				Message m = new Message(rs.getInt("msg_num"),
										rs.getString("msg_title"),
										rs.getDate("msg_date"),
										rs.getString("msg_con"),
										rs.getString("rnick"),
										rs.getString("snick"));
				mList.add(m);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println("mList1 + " + mList);
		return mList;
	}

	public Message selectMessage(Connection conn, int mNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message message = null;
		String query = prop.getProperty("selectMessage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				message = new Message(rs.getInt("msg_num"),
									  rs.getString("msg_title"),
									  rs.getDate("msg_date"),
									  rs.getString("msg_con"),
									  rs.getString("rnick"),
									  rs.getString("snick"),
									  rs.getString("rsg_id"),
									  rs.getString("ssg_id"),
									  rs.getString("rsg_del"),
									  rs.getString("ssg_del"),
									  rs.getString("msg_status"));
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		 
		return message;
	}

	public int deleteMessage(Connection conn, int mNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMessageR");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNum);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Message selectSendMessage(Connection conn, int mNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message message = null;
		String query = prop.getProperty("selectSendMessage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				message = new Message(rs.getInt("msg_num"),
									  rs.getString("msg_title"),
									  rs.getDate("msg_date"),
									  rs.getString("msg_con"),
									  rs.getString("rnick"),
									  rs.getString("snick"),
									  rs.getString("rsg_id"),
									  rs.getString("ssg_id"),
									  rs.getString("rsg_del"),
									  rs.getString("ssg_del"),
									  rs.getString("msg_status"));
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("message " + message);

		return message;
	}


	public int getListCounts(Connection conn, String ssgId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCounts");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ssgId);
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
		
		System.out.println("ListhCounts " + result);
		return result;
	}

	
	
	public ArrayList<Message> selectSendList(Connection conn, int currentPages, String ssgId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Message> mLists = null;

		int postss = 10; 
		int startRows = (currentPages -1) * postss + 1; 
		int endRows = startRows + postss -1;
		
		System.out.println("startRows = "+startRows);
		System.out.println("endRows = "+endRows);
		System.out.println("currenP + " + currentPages);
		String query = prop.getProperty("selectSendMlist");
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRows);
			pstmt.setInt(2, endRows);
			pstmt.setString(3, ssgId);
			rs = pstmt.executeQuery();
			mLists = new ArrayList<Message>();
			while(rs.next()) {
				Message m = new Message(rs.getInt("msg_num"),
										rs.getString("msg_title"),
										rs.getDate("msg_date"),
										rs.getString("msg_con"),
										rs.getString("rnick"),
										rs.getString("snick"),
										rs.getString("rsg_id"),
										rs.getString("ssg_id"),
										rs.getString("rsg_del"),
										rs.getString("ssg_del"),
										rs.getString("msg_status"));
				mLists.add(m);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println("mLists제발" + mLists);
		return mLists;

	}

	public int insertMessage(Connection conn, String rsgId, String ssgId, String title, String con) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMessage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, con);
			pstmt.setString(3, rsgId);
			pstmt.setString(4, ssgId);
			
			System.out.println("titleDAO + " + title);
			System.out.println("conDAO + " + con);
			System.out.println("누가 받지?DAO + " + rsgId);
			System.out.println("누가 보냈지?DAO + " + ssgId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}