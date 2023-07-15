package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.service.DishService;

@Component
public class DishValidator implements Validator {
	
	@Autowired
	private DishService dishService;

	@Override
	public void validate(Object o, Errors errors) {
		Dish dish = (Dish)o;
		if(dishService.getName(dish) != null
		&& dishService.existsByName(dishService.getName(dish)))
			errors.reject("dish.duplicate");
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Dish.class.equals(aClass);
	}
}
