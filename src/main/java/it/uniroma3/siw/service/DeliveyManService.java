package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Deliveryman;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.OrderState;
import it.uniroma3.siw.repository.DeliveryManRepository;
import jakarta.transaction.Transactional;

@Service
public class DeliveyManService {

	@Autowired
	private DeliveryManRepository deliveryManRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Transactional
	public Iterable<Order> getOrdersByState(Deliveryman deliveryman,OrderState orderState) {
		return orderService.findOrdersByState(deliveryman.getId(),orderState);
	}

	@Transactional
	public Deliveryman getDeliveryMan(Long idu) {
		return deliveryManRepository.findById(idu).orElse(null);
	}
	
	@Transactional
	public void save(Deliveryman deliveryman) {
		deliveryManRepository.save(deliveryman);
	}
	
	
	@Transactional
	public int countOrdersByState(Deliveryman deliveryman, OrderState orderState) {
		return orderService.countOrdersByState(deliveryman.getId(),orderState);
	}

	public Long getId(Deliveryman deliveryman) {
		return deliveryman.getId();
	}
	

	@Transactional
	public void takeOrder(Deliveryman deliveryman, Order order) {
		
		orderService.setOrderState(order, OrderState.SPEDITO);
    	orderService.setDeliveryman(order, deliveryman);
    	increaseNumOfOrdersDelivering(deliveryman);
    	save(deliveryman);		
	}
	
	@Transactional
	public void increaseNumOfOrdersDelivering(Deliveryman deliveryman) {
		deliveryman.increaseNumOfOrdersDelivering();
		save(deliveryman);
	}
	
	
	@Transactional
	public void decreaseNumOfOrdersDelivering(Deliveryman deliveryman) {
		deliveryman.decreaseNumOfOrdersDelivering();
		save(deliveryman);
	}


	public boolean isOccupied(Deliveryman deliveryman) {
		return deliveryman.isOccupied();
	}
	
	@Transactional
	public void deliverOrder(Deliveryman deliveryman, Order order) {
		orderService.orderDelivered(order);
		deliveryman.decreaseNumOfOrdersDelivering();
		save(deliveryman);
	}
	
	
}
