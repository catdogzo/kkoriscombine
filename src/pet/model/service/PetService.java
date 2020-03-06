package pet.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import pet.model.dao.PetDAO;
import pet.model.vo.Pet;

public class PetService {

	public ArrayList<Pet> listPet(String userId) {
		Connection conn = getConnection();
		ArrayList<Pet> petList = new PetDAO().listPet(conn, userId);
		
		close(conn);
		return petList;
	}

	public Pet selectPet(int petNum) {
		Connection conn = getConnection();
		Pet pet = new PetDAO().selectPet(conn, petNum);
		
		close(conn);
		return pet;
	}
	
	public int insertPet(Pet pet) {
		Connection conn = getConnection();
		
		PetDAO pDAO = new PetDAO();
		int result = pDAO.insertPet(conn, pet);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deletePet(int petNum) {
		Connection conn = getConnection();
		PetDAO pDAO = new PetDAO();
		
		int result = pDAO.deletePet(conn, petNum);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public int updatePet(Pet pet) {
		Connection conn = getConnection();
		PetDAO pDAO = new PetDAO();
		
		int result = pDAO.updatePet(conn, pet);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

}
