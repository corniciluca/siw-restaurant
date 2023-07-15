package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.OrderState;

public interface OrderRepository extends CrudRepository<Order, Long> {

	Iterable<Order> findAllByOrderState(OrderState orderState);
	
	@Query(value = "Select o FROM Order o WHERE o.deliveryman.id= :idd AND o.orderState = :orderState")
	Iterable<Order> findOrdersByState(@Param("idd") Long idd,@Param("orderState") OrderState orderState);
	
	@Query(value = "Select COUNT(o) FROM Order o WHERE o.deliveryman.id= :idd AND o.orderState = :orderState")
	int countOrdersByState(@Param("idd") Long idd, @Param("orderState") OrderState orderState);
}
