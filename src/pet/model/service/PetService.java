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

}
