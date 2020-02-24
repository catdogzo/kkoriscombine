package pet.model.dao;

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

import pet.model.vo.Pet;

public class PetDAO {
	private Properties prop = new Properties();
	
	public PetDAO() {
		String fileName = PetDAO.class.getResource("/sql/pet/pet-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pet> listPet(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Pet> petList = null;
		
		String query = prop.getProperty("listPet");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			petList = new ArrayList<Pet>();
			while(rs.next()) {
				petList.add(new Pet(rs.getInt("PET_NUM"),
									rs.getString("PET_NAME"),
									rs.getString("PET_GEN"),
									rs.getString("PET_SPEC"),
									rs.getDouble("PET_WEIGHT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return petList;
	}

	public Pet selectPet(Connection conn, int petNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Pet pet = null;
		
		String query = prop.getProperty("selectPet");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, petNum);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pet = new Pet(rs.getInt("PET_NUM"),
							  rs.getString("PET_NAME"),
							  rs.getDate("PET_BIRTH"),
							  rs.getString("PET_GEN"),
							  rs.getString("PET_CATE"),
							  rs.getString("PET_SPEC"),
							  rs.getDouble("PET_WEIGHT"),
							  rs.getString("PET_VACC"),
							  rs.getString("PET_NEUT"),
							  rs.getDate("PET_ADD"),
							  rs.getDate("PET_UPDATE"),
							  rs.getString("US_ID"),
							  rs.getString("PET_DEL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return pet;
	}
}
