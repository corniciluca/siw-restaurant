package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.validator.DishValidator;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.model.DishType;
import it.uniroma3.siw.service.CustomerService;
import it.uniroma3.siw.service.DishService;
import jakarta.validation.Valid;

@Controller
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DishValidator dishValidator;
	
	
	@GetMapping("/menu") 
	public String menu(Model model) {
		model.addAttribute("dishes", dishService.getDishesByTypeArray());
		model.addAttribute("favoriteDishes",null);
        return "menu.html";
	}
	
	@GetMapping("/menu/{idc}") 
	public String menu(@PathVariable("idc") Long idc,Model model) {
		Customer customer = customerService.getCustomer(idc);
		model.addAttribute("dishes", dishService.getDishesByTypeArray());
		model.addAttribute("favoriteDishes", customerService.getFavoritiesDishes(customer));		
        return "menu.html";
	}
	
	@GetMapping("/preferences/{idc}")
	public String preferences(@PathVariable("idc") Long idc,Model model) {
		Customer customer = customerService.getCustomer(idc);
		model.addAttribute("favoriteDishes", customerService.getFavoritiesDishes(customer));
		return "preferences.html";
	}
	
	@GetMapping("/addOrRemoveFavorities/{idc}/{idd}") 
	public String addOrRemoveFavorities(@PathVariable("idc") Long idc,@PathVariable("idd") Long idd,Model model) {
		
		Customer customer = customerService.getCustomer(idc);
		Dish dish = dishService.getDish(idd);
		
		customerService.addOrRemoveFavorite(customer,dish);
		
		return "redirect:/menu/"+idc;
	}
	
	@GetMapping("/admin/deleteDish/{idd}")
	public String deleteDish(@PathVariable("idd") Long idd,Model model) {
		Dish dish = dishService.getDish(idd);
		dishService.delete(dish);
		return "redirect:/admin/manageDishes/";
	}
	
	
	@PostMapping("/admin/updateDishImage/{idd}")
	public String updateDishImage(@PathVariable("idd") Long idd,@RequestParam("image") MultipartFile image,Model model) throws IOException {
		
		Dish dish = dishService.getDish(idd);
		dishService.uploadImageOfDish(dish, image);

		return "redirect:/admin/manageDishes/";
	}
	
	@PostMapping("/admin/updateDish/{idd}")
	public String updateDish(@PathVariable("idd") Long idd,@Valid @ModelAttribute("dishM") Dish modifiedDish,BindingResult bindingResult,Model model) throws IOException {
		
		Dish dishInDB = dishService.getDish(idd);
		
		if(!bindingResult.hasErrors()) {
			
			dishService.updateDishAttributes(dishInDB,modifiedDish);	
			return "redirect:/admin/manageDishes/";
		}
		
		model.addAttribute("dishes", dishService.getDishesByTypeArray());
		model.addAttribute("dishTypes",DishType.values());
		model.addAttribute("dishM",modifiedDish);
		
        return "/admin/manageDishes.html";
		
	}
	
	@PostMapping("/admin/addNewDish/")
	public String  newDish(@Valid @ModelAttribute("dishM") Dish newDish,BindingResult bindingResult ,@RequestParam("imageDish") MultipartFile image,Model model) throws IOException {
		
		dishValidator.validate(newDish,bindingResult);
		
		if(!bindingResult.hasErrors()) {
			dishService.uploadImageOfDish(newDish, image);
			return "redirect:/admin/manageDishes/";
		}
		
		model.addAttribute("dishes", dishService.getDishesByTypeArray());
		model.addAttribute("dishTypes",DishType.values());
		model.addAttribute("dishM",newDish);
		
		return "/admin/manageDishes.html";
		
	}

	@GetMapping("/admin/manageDishes/") 
	public String manageDishes(Model model) {
		
		model.addAttribute("dishes", dishService.getDishesByTypeArray());
		model.addAttribute("dishTypes",DishType.values());
		model.addAttribute("dishM",new Dish());
		
        return "/admin/manageDishes.html";
	}
}
