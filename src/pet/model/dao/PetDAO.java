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
		// listPet=SELECT * FROM PET WHERE US_ID = ? AND PET_DEL = 'N'
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			petList = new ArrayList<Pet>();
			while(rs.next()) {
				petList.add(new Pet(rs.getInt("PET_NUM"),
									rs.getString("PET_NAME"),
									rs.getDate("PET_BIRTH"),
									rs.getString("PET_GEN"),
									rs.getString("PET_CATE"),
									rs.getString("PET_SPEC"),
									rs.getDouble("PET_WEIGHT"),
									rs.getString("PET_VACC"),
									rs.getString("PET_NEUT"),
									rs.getString("US_ID")));
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
		// selectPet=SELECT * FROM PET WHERE PET_NUM = ?
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
	
	public int insertPet(Connection conn, Pet pet) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertPet");
		// insertPet=INSERT INTO PET VALUES(SEQ_PTN.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, DEFAULT)
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pet.getPetName());
			pstmt.setDate(2, pet.getPetBirth());
			pstmt.setString(3, pet.getPetGender());
			pstmt.setString(4, pet.getPetCate());
			pstmt.setString(5, pet.getPetSpec());
			pstmt.setDouble(6, pet.getPetWeight());
			pstmt.setString(7, pet.getPetVacc());
			pstmt.setString(8, pet.getPetNeut());
			pstmt.setString(9, pet.getUsId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deletePet(Connection conn, int petNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deletePet");
		// deletePet=UPDATE PET SET PET_DEL = 'Y' WHERE PET_NUM = ?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, petNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updatePet(Connection conn, Pet pet) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePet");
		// updatePet=UPDATE PET SET PET_NAME=?, PET_GEN=?, PET_BIRTH=?, PET_CATE=?, PET_SPEC=?, PET_WEIGHT=?, PET_VACC=?, PET_NEUT=? WHERE PET_NUM=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pet.getPetName());
			pstmt.setString(2, pet.getPetGender());
			pstmt.setDate(3, pet.getPetBirth());
			pstmt.setString(4, pet.getPetCate());
			pstmt.setString(5, pet.getPetSpec());
			pstmt.setDouble(6, pet.getPetWeight());
			pstmt.setString(7, pet.getPetVacc());
			pstmt.setString(8, pet.getPetNeut());
			pstmt.setInt(9, pet.getPetNum());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
