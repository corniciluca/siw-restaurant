package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.OrderLine;
import it.uniroma3.siw.repository.OrderLineRepository;
import jakarta.transaction.Transactional;
@Service
public class OrderLineService {
	
	@Autowired
	private OrderLineRepository orderLineRepository;

	@Transactional
	public void save(OrderLine line) {
		orderLineRepository.save(line);
	}
	
	@Transactional
	public void delete(OrderLine orderLine) {
		orderLineRepository.delete(orderLine);
	}
	
	@Transactional
	public OrderLine getOrderLine(Long idl) {
		return orderLineRepository.findById(idl).orElse(null);
	}

	public void initializeAttributes(OrderLine orderLine, Dish dish, int qty, Order order) {
		orderLine.setDish(dish);
    	orderLine.setQuantity(qty);
    	orderLine.setOrder(order);
    	orderLine.calcPrice();
	}

}
