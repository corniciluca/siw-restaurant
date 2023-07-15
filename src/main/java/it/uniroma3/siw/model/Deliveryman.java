package it.uniroma3.siw.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Deliveryman extends User{
	
	@NotNull
	@Column(nullable = false)
	private int numOrdersDelivering;
	
	private static final int MAX_ORDERS_DELIVERING = 3;
	
	@OneToMany(mappedBy = "deliveryman",cascade = {CascadeType.PERSIST})
	private Set<Order> orders;

	public Deliveryman() {}
	
	public Deliveryman(String name,String surname,String email) {
		super(name,surname,email,Role.DELIVERYMAN);
		setNumOrdersDelivering(0);
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public boolean isOccupied() {
		return numOrdersDelivering >= MAX_ORDERS_DELIVERING;
	}

	public int getNumOrdersDelivering() {
		return numOrdersDelivering;
	}

	public void setNumOrdersDelivering(int numOrdersDelivering) {
		this.numOrdersDelivering = numOrdersDelivering;
	}

	public void increaseNumOfOrdersDelivering() {
		numOrdersDelivering++;
	}

	public void decreaseNumOfOrdersDelivering() {
		numOrdersDelivering--;
	}


}
