package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Feedback;
import it.uniroma3.siw.service.FeedbackService;

@Component
public class FeedbackValidator implements Validator{
	
	@Autowired
	private FeedbackService feedbackService;

	@Override
	public void validate(Object o, Errors errors) {
		Feedback feedback = (Feedback)o;
		if(feedbackService.getOrder(feedback) != null
		&& feedbackService.existsByOrder(feedbackService.getOrder(feedback)))
			errors.reject("feedback.duplicate");
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Feedback.class.equals(aClass);
	}
}
