package com.capg.main.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.main.pojo.Food;
import com.capg.main.service.FoodService;


@RestController
@RequestMapping("/food/")
public class FoodController {

	@Autowired
	FoodService fservice;

    //Retrive data from table
	@GetMapping("getProduct/{itemid}")
	
	public ResponseEntity<Object> getProductDetails(@PathVariable("itemid") int itemid) {
		Optional<Food> op = fservice.Detail(itemid);
		if (op.isPresent()) {
			Food food = op.get();
			return new ResponseEntity<Object>(food, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Item is not present", HttpStatus.NOT_FOUND);
		}

	}

	

	// Insert item in Table food
	@PostMapping("/save")
	public ResponseEntity<String> saveFood(@RequestBody  @Valid Food food) {
		
		 fservice.saveFood(food);
		return new ResponseEntity<String>("Successfully added", HttpStatus.CREATED);

	}
	
	// Get all food item
		@GetMapping("/allFood")
		
		public List<Food> getAllFood() {
			List<Food> list = fservice.AllFood();
			return list;
		}
	
		// Delete Item by id
		@DeleteMapping("/deleteFood/{itemid}")
		public ResponseEntity<String> deleteFoodById(@PathVariable("itemid") int itemid) {
			String s=fservice.deleteFood(itemid);
			if (s.equals("delete")) {
				
				return new ResponseEntity<String>("Delete successfully", HttpStatus.OK);

						}
			return new ResponseEntity<String>("Food is not present in the list", HttpStatus.NOT_FOUND);


		}

		// Update Food item by ID
		@PutMapping("/updateFood/{itemid}")
		public ResponseEntity<Object> UpdateFoodById(@PathVariable("itemid") int itemid, @RequestBody Food food) {
			Food f=fservice.updateFood(itemid, food);
			if(f!=null) {
				return new ResponseEntity<Object>(f, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("NotFound", HttpStatus.NOT_FOUND);
			}

		}
		
		@GetMapping("/getfoodbyprice/{price}")
		public ResponseEntity<Object> getFoodByPrice(@PathVariable("price") int price) {
			List<Food>f=fservice.findPrice(price);
			if(f.size()>0) {
				return new ResponseEntity<Object>(f, HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		
		
		@GetMapping("/getProductPriceinBetween/{sprice}/{eprice}")
		public ResponseEntity<Object> getProductInRange(@PathVariable("sprice")int sprice, @PathVariable("eprice") int eprice)
		{
			List<Food>f=fservice.PriceBetween(sprice, eprice);
			if(f.size()>0) {
				return new ResponseEntity<Object>(f, HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Item Not found", HttpStatus.NOT_FOUND);
		
		}
		
		@GetMapping("/getFoodByName/{itemname}")
		public ResponseEntity<Object> getFoodByName(@PathVariable("itemname") String itemname)
		{
			Food f= fservice.ByName(itemname);
			if(f!=null) {
				return new ResponseEntity<Object>(f, HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Item Not found", HttpStatus.NOT_FOUND);
		}
		
		@GetMapping("/foodGreaterThanPrice/{price}")
		public ResponseEntity<Object> getFoodGreaterThanPrice(@PathVariable("price") int price)
		{
			List<Food>f= fservice.foodPriceGreater(price);
			if(f.size()>0) {
				return new ResponseEntity<Object>(f, HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Item Not found", HttpStatus.NOT_FOUND);
		}
		

		

	
}

