package com.capg.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.main.pojo.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food, Integer> {

	@Query(value = "select f from Food f where f.price=:price")
	List<Food> findByPrice(@Param("price")int price);
	
	
	@Query(value="select f from Food f where f.price between :sprice and :eprice")
	List<Food> findByPriceInBetween(@Param("sprice") int sprice, @Param("eprice") int eprice);
	
	
	@Query(value="select f from Food f where f.itemname=:itemname")
	Food findByName(@Param("itemname") String itemname);
	
	
	@Query(value="select f from Food f where f.price>:price")
	List<Food> findFoodGreaterThanPrice(@Param("price") int price);
	
	
	@Query(value="select f from Food f order by f.price")
	List<Food> orderInDescending(@Param("price") int price);
}
