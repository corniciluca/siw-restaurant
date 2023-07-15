package it.uniroma3.siw.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.repository.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderService orderService;
	
	@Transactional
	public Customer getCustomer(Long idc) {
		return customerRepository.findById(idc).orElse(null);
	}
	
	public Set<Order> getOrders(Customer customer) {
		return customer.getOrders();
	}
	
	@Transactional
	public void deleteOrderFormCustomer(Customer customer, Order order) {
		customer.getOrders().remove(order);
		orderService.delete(order);
	}
	
	@Transactional
	public void save(Customer customer) {
		customerRepository.save(customer);	
	}

	public Long getId(Customer customer) {
		return customer.getId();
	}

	@Transactional
	public void addOrRemoveFavorite(Customer customer, Dish dish) {
		Set<Dish> favoriteDishes = customer.getFavoriteDishes();
		if(favoriteDishes.contains(dish))
			favoriteDishes.remove(dish);
		else
			favoriteDishes.add(dish);
			
		save(customer);
	}

	public Set<Dish> getFavoritiesDishes(Customer customer) {
		return customer.getFavoriteDishes();
	}
}
