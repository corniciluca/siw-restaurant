package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Column(nullable = false)
	private LocalDateTime timeOfOrdering;
	
	private LocalDateTime timeOfDelivery;
	
	@Min(value = 0)
	private double totalPrice;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(nullable = false)
	private OrderState orderState;
	
	@ManyToOne
	@NotNull
	private Customer customer;
	
	@ManyToOne
	private Deliveryman deliveryman;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private Feedback feedback;
	
	@OneToMany(mappedBy="order",cascade = {CascadeType.ALL} )
	private Set<OrderLine> orderLines;
	
	public Order() {}
	
	public Order(Customer customer,LocalDateTime timeOfOrdering,OrderState orderState)
	{
		setCustomer(customer);
		setTimeOfOrdering(timeOfOrdering);
		setOrderState(orderState);	
	}
	
	public void calcTotalPrice() {
		for (OrderLine line : orderLines) {
			totalPrice += line.getOrderLinePrice();
		}
	}

	public LocalDateTime getTimeOfOrdering() {
		return timeOfOrdering;
	}

	public void setTimeOfOrdering(LocalDateTime timeOfOrdering) {
		this.timeOfOrdering = timeOfOrdering;
	}

	public LocalDateTime getTimeOfDelivery() {
		return timeOfDelivery;
	}

	public void setTimeOfDelivery(LocalDateTime timeOfDelivery) {
		this.timeOfDelivery = timeOfDelivery;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Deliveryman getDeliveryman() {
		return deliveryman;
	}

	public void setDeliveryman(Deliveryman deliveryman) {
		this.deliveryman = deliveryman;
	}

	public Set<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, id, timeOfOrdering);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customer, other.customer) && id == other.id
				&& Objects.equals(timeOfOrdering, other.timeOfOrdering);
	}
	
}
