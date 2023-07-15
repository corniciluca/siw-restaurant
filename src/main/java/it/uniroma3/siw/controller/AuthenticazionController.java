package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Role;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.FeedbackService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthenticazionController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	@Autowired
	private UserValidator userValidator;
	
	
	@GetMapping("/") 
	public String index(Model model) {
		float avg = feedbackService.getTotalAvgRating();
        model.addAttribute("rating", avg);
        return "index.html";
	}
		
    @GetMapping("/success")
    public String defaultAfterLogin(Model model) {
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        return "login.html";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
    	model.addAttribute("customer", new Customer());
    	model.addAttribute("credentials", new Credentials());
        return "register.html";
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("customer") Customer customer,
                 BindingResult userBindingResult, @Valid
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,Model model) {
		
		userService.inizializeUser(customer, Role.CUSTOMER);
		credentialsService.setUsername(credentials,credentialsService.getUsername(credentials).toLowerCase());
		
		userValidator.validate(customer, credentialsBindingResult);
		credentialsValidator.validate(credentials, credentialsBindingResult);
		
        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            credentialsService.setUser(credentials,customer);
            return "redirect:/login";
        }
        
        model.addAttribute("user", customer );
		model.addAttribute("credentials", credentials);
		return "register.html";
    }
    
}
