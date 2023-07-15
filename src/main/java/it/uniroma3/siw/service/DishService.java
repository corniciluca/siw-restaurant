package it.uniroma3.siw.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.model.DishType;
import it.uniroma3.siw.repository.DishRepository;
import it.uniroma3.siw.utils.FileUploadUtil;
import jakarta.transaction.Transactional;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;
	
	@Transactional
	public Iterable<Dish> getDishesByType(DishType dishType){
		Iterable<Dish> dishes = dishRepository.findByDishType(dishType);
		return dishes != null ? dishes : new ArrayList<Dish>();
	}
	@Transactional
	public Dish getDish(Long idDish) {
		return dishRepository.findById(idDish).orElse(null);
	}
	@Transactional
	public  Iterable<Dish> getAllDishes() {
		return dishRepository.findAll();
	}
	@Transactional
	public void delete(Dish dish) {
		dishRepository.delete(dish);
	}
	
	@Transactional
	public void updateDishAttributes(Dish dishInDB,  Dish modifiedDish) {
		dishRepository.updateDishAttributes(dishInDB.getId(),modifiedDish.getName(), modifiedDish.getDescription(), modifiedDish.getDishType(), modifiedDish.getPrice());
	}

	public void updateDishFields(Dish dish, Dish modifiedDish) {
		for (Field field : modifiedDish.getClass().getDeclaredFields()) {
			if(field.getName() != "id")
				updateDieshField(dish,modifiedDish,field.getName());
		}
		save(dish);
	}	
	
	private void updateDieshField(Dish dish,Dish modifiedDish,String nameField) {
		try {
			Class<?> typeClass = Dish.class.getDeclaredField(nameField).getType();
			Method setter = Dish.class.getMethod("set"+StringUtils.capitalize(nameField), typeClass);
		    Method getter = Dish.class.getMethod("get"+StringUtils.capitalize(nameField));
		    
		    if (getter.invoke(modifiedDish) != null)
				setter.invoke(dish, getter.invoke(modifiedDish));
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	@Transactional
	public void save(Dish dish) {
		dishRepository.save(dish);
		}

	public String getImageOfDish(Dish dish) {
		return dish.getImage();
	}

	public void uploadImageOfDish(Dish dish, MultipartFile image) throws IOException {
		String filename = StringUtils.cleanPath(image.getOriginalFilename());
		FileUploadUtil.saveFile("./"+Dish.PATH_IMAGES+dish.getDishType(), filename, image);
		dish.setImage(filename);
		save(dish);
	}

	public List<Iterable<Dish>> getDishesByTypeArray() {
		List<Iterable<Dish>> dishes = new ArrayList<>();
		dishes.add(getDishesByType(DishType.PRIMO));
		dishes.add(getDishesByType(DishType.SECONDO));
		dishes.add(getDishesByType(DishType.CONTORNO));
		dishes.add(getDishesByType(DishType.BEVANDA));
		dishes.add(getDishesByType(DishType.DOLCE));
		return dishes; 
	}

	public String getName(Dish dish) {
		return dish.getName();
	}
	
	@Transactional
	public boolean existsByName(String name) {
		return dishRepository.existsByName(name);
	}
	
}
