package com.capg.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.main.pojo.Food;
import com.capg.main.repository.FoodRepository;

@Service
public class FoodService {
	@Autowired
	private FoodRepository repo;

	
	public void saveFood(Food food) {
		
		repo.save(food);
		return ;
	}
	
	public Optional<Food> Detail(int itemid) {
		return repo.findById(itemid);
	}
	
	public List<Food> AllFood(){
		List<Food> list = new ArrayList();
		repo.findAll().forEach(list::add);
		return list;
	}

	public String deleteFood(int itemid){
		Optional<Food> op = repo.findById(itemid);
		if (op.isPresent()) {
			repo.deleteById(itemid);
			return "delete";
	}
	return "notDeleted"	;
	}
	
	public Food updateFood(int itemid,Food food) {
		Optional<Food> op = repo.findById(itemid);
		if (op.isPresent()) {
			Food food2 = op.get();
			food2.setItemname(food.getItemname());
			food2.setPrice(food.getPrice());
			repo.save(food2);
			return food2;
		} else {
			return null;
		}
	}
	
	public List<Food>findPrice(int price){
		List<Food>f=repo.findByPrice(price);
		
			return f;
		
	}
	
	public List<Food>PriceBetween(int sprice,int eprice){
		List<Food>f=repo.findByPriceInBetween(sprice, eprice);
			return f;
		
	}
	
	public Food ByName(String itemname) {
		Food f= repo.findByName(itemname);
		return f;
	}
	
	
	
	public List<Food>foodPriceGreater(int price){
		List<Food>f= repo.findFoodGreaterThanPrice(price);
		
			return f;
		
	}
}
