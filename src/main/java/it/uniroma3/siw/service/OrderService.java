package it.uniroma3.siw.service;

import static it.uniroma3.siw.model.OrderState.IN_ATTESA;
import static it.uniroma3.siw.model.OrderState.PRONTO;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Deliveryman;
import it.uniroma3.siw.model.Feedback;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.OrderLine;
import it.uniroma3.siw.model.OrderState;
import it.uniroma3.siw.repository.OrderRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;

	
	@Transactional
	public void save(Order order) {
		orderRepository.save(order);
	}
	
	@Transactional
	public Order getOrder(Long ido) {
		return orderRepository.findById(ido).orElse(null);
	}

	@Transactional
	public void delete(Order order) {
		orderRepository.delete(order);
	}
	
	@Transactional
	public Iterable<Order> getOrdersByState(OrderState orderState) {
		return orderRepository.findAllByOrderState(orderState);
	}

	public Customer getCustomer(Order order) {
		return order.getCustomer();
	}
	
	@Transactional
	public void setFeedback(Order order,Feedback feedback) {
		order.setFeedback(feedback);
		save(order);
	}
	
	@Transactional
	public void addOrderLine(Order order, OrderLine orderLine) {
		order.getOrderLines().add(orderLine);
		save(order);
	}
	
	@Transactional
	public void setReady(Order order) {
		order.calcTotalPrice();
		order.setOrderState(PRONTO);
		save(order);
	}
	
	@Transactional
	public Order createNewOrder(Customer customer) {
		Order order = new Order(customer,LocalDateTime.now(),IN_ATTESA);
		save(order);
		return order;
	}

	public long getId(Order order) {
		return order.getId();
	}
	
	@Transactional
	public Iterable<Order> findOrdersByState(long id, OrderState orderState) {
		return orderRepository.findOrdersByState(id, orderState);
	}
	@Transactional
	public int countOrdersByState(long id, OrderState orderState) {
		return orderRepository.countOrdersByState(id, orderState);
	}
	
	public void setOrderState(Order order,OrderState orderState) {
		order.setOrderState(orderState);
	}

	public void setDeliveryman(Order order,Deliveryman deliveryman) {
		order.setDeliveryman(deliveryman);
	}
	
	public void orderDelivered(Order order) {
		order.setOrderState(OrderState.CONSEGNATO);
    	order.setTimeOfDelivery(LocalDateTime.now());
	}

	public Deliveryman getDeliveryman(Order order) {
		return order.getDeliveryman();
	}

	public boolean isEmpty(Order order) {
		return order.getOrderLines().isEmpty();
	}


}
