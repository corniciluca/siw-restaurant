package it.uniroma3.siw.authentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.service.CustomerService;
import it.uniroma3.siw.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccesHandler implements AuthenticationSuccessHandler{

	@Autowired UserService userService; 
	@Autowired CustomerService customerService; 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if(authentication instanceof OAuth2AuthenticationToken) {
			DefaultOAuth2User auth2User = (DefaultOAuth2User) authentication.getPrincipal();
			String name = auth2User.getAttribute("given_name");
			String surname = auth2User.getAttribute("family_name");
			String email = auth2User.getAttribute("email");
			if(!userService.existsByNameAndSurname(name,surname)) {
				Customer newUser = new Customer(name,surname,email,"");
				userService.saveUser(newUser);
				customerService.save(newUser);
			}
		}
		new DefaultRedirectStrategy().sendRedirect(request, response, "/");
		
	}

}
