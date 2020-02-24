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

}
