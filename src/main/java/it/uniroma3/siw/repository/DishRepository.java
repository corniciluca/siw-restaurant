package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.model.DishType;

public interface DishRepository extends CrudRepository<Dish, Long>{
	
	public Iterable<Dish> findByDishType(DishType dishType);

	public boolean existsByName(String name);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Dish AS d SET d.name = :name , d.description = :description , d.dishType = :type , d.price = :price WHERE d.id = :id")
	public void updateDishAttributes(@Param("id") Long id,@Param("name") String name,@Param("description") String description,@Param("type") DishType type,@Param("price") float price);
	
}
