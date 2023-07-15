package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Feedback;
import it.uniroma3.siw.model.Order;

public interface FeedbackRepository extends CrudRepository<Feedback, Long>{
	
	public boolean existsByOrder (Order order);
	
	@Query(value = "SELECT AVG(rating) FROM Feedback",nativeQuery = true)
	public Double getTotalAvgRating();
	
	@Query(value = "SELECT AVG(f.rating) FROM Feedback f WHERE f.order.deliveryman.id = :idd")
	public Double getAvgRatingDeliveryman(@Param("idd") Long idd);

}
