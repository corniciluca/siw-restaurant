package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Deliveryman;
import it.uniroma3.siw.model.Feedback;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.repository.FeedbackRepository;
import jakarta.transaction.Transactional;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private DeliveyManService deliveymanService;
	
	@Transactional
	public void setOrder(Feedback feedback, Order order) {
		feedback.setOrder(order);
		save(feedback);
	}
	
	@Transactional
	public void save(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
	
	@Transactional
	public float getTotalAvgRating() {
		Double totalAvgRating = feedbackRepository.getTotalAvgRating();
		return  (totalAvgRating == null) ? 0 : totalAvgRating.floatValue();
	}

	@Transactional
	public float getRatingOfDeliveryman(Deliveryman deliveryman) {
		Double rating = feedbackRepository.getAvgRatingDeliveryman(deliveymanService.getId(deliveryman));
		return rating != null ? rating.floatValue() : 0;
	}

	public Order getOrder(Feedback feedback) {
		return feedback.getOrder();
	}
	
	@Transactional
	public boolean existsByOrder(Order order) {
		return feedbackRepository.existsByOrder(order);
	}

}
