package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.FeedbackValidator;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Feedback;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.service.CustomerService;
import it.uniroma3.siw.service.FeedbackService;
import it.uniroma3.siw.service.OrderService;
import jakarta.validation.Valid;

@Controller
public class FeedbackController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private FeedbackValidator feedbackValidator;
	
	@PostMapping("/leaveAFeedback/{ido}")
	public String leaveAFeedback(@PathVariable("ido") Long ido,@Valid @ModelAttribute("feedback") Feedback feedback,BindingResult bindingResult,Model model) throws IOException {
		Order order = orderService.getOrder(ido);
		Customer customer = orderService.getCustomer(order);
		feedbackValidator.validate(feedback, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			feedbackService.setOrder(feedback,order);
			orderService.setFeedback(order,feedback);
			
			return "redirect:/myOrdersCustomer/"+customerService.getId(customer);
		}
		

		model.addAttribute("orders", customerService.getOrders(customer));
    	model.addAttribute("feedback", feedback);
    	
    	return "myOrders.html";
	}
	


}
