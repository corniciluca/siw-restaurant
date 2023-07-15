package it.uniroma3.siw.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{

	public Admin() {}
	
	public Admin(String name,String surname,String email) {
		super(name,surname,email,Role.ADMIN);
	}

}
