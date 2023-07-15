package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Column(nullable = false)
	private int quantity;
	
	@Min(value = 0)
	private float orderLinePrice;
	
	@OneToOne
	@NotNull
	private Dish dish;
	
	@ManyToOne
	private Order order;
	
	public OrderLine() {}
	
	public OrderLine(Dish dish,int qty,float price,Order order) {
		setDish(dish);
		setOrderLinePrice(price);
		setQuantity(qty);
		setOrder(order);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getOrderLinePrice() {
		return orderLinePrice;
	}

	public void setOrderLinePrice(float orderLinePrice) {
		this.orderLinePrice = orderLinePrice;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dish, order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		return Objects.equals(dish, other.dish) && Objects.equals(order, other.order);
	}

	public void calcPrice() {
		setOrderLinePrice(getQuantity()*getDish().getPrice());
	}

}
