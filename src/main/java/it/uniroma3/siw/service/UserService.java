package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Role;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired UserRepository userRepository;
	
	@Transactional
	public User getUser(Long idUser){
		return userRepository.findById(idUser).orElseGet(null);
	}
	
	@Transactional
	public User getUser(String name,String surname){
		return userRepository.findByNameAndSurname(name,surname).orElse(null);
	}
	
	@Transactional
	public User saveUser(User user){
		return userRepository.save(user);
	}
	
	@Transactional
	public boolean existsByNameAndSurname(String name, String surname) {
		return userRepository.existsByNameAndSurname(name,surname);
	}

	public String getName(User user) {
		return user.getName();
	}

	public String getSurname(User user) {
		return user.getSurname();
	}
	
	public long getId(User u) {
		return u.getId();
	}

	public void setRoleOfUser(User user,Role role) {
		user.setRole(role);
	}

	public void setName( User user, String name) {
		user.setName(name);
	}
	
	public void setSurname( User user, String surname) {
		user.setSurname(surname);
	}

	public void inizializeUser(User user,Role role) {
		setName(user,getName(user).toLowerCase());
		setSurname(user,getSurname(user).toLowerCase());
		setRoleOfUser(user,role);		
	}
	
}
