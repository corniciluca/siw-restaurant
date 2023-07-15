package it.uniroma3.siw.model;

import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer extends User{

	
	private String address;
	
	//TODO: Pattern
	@Pattern(regexp = "^[0-9]+$")
	@Length(max=10)
	@Column(length = 10)
	private String phoneNumber;
	
	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;
	
	@OneToMany
	private Set<Dish> favoriteDishes;
	
	public Customer() {	}
	
	public Customer(String name,String surname,String email,String address) {
		super(name,surname,email,Role.CUSTOMER);
		setAddress(address);
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Dish> getFavoriteDishes() {
		return favoriteDishes;
	}

	public void setFavoriteDishes(Set<Dish> favoriteDishes) {
		this.favoriteDishes = favoriteDishes;
	}
}
