package com.capg.main.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capg.main.pojo.Product;
import com.capg.main.repository.ProductRepository;

@RestController
public class ProductController {
	
	
/*	@GetMapping("/hello1")
	public String message1() {
		return "received get request";
		
	}
	@PostMapping("/hello2")
	public String message2() {
		return "received post request";
		
	}
	@PutMapping("/hello3")
	public String message3() {
		return "received put request";
		
	}
	@DeleteMapping("/hello4")
	public String message4() {
		return "received delete request";
		
	}
*/
	@Autowired
	ProductRepository repo;
	
	@GetMapping("/getproduct/{id}")
	public Product getProductDetails(@PathVariable("id") int id) {
		Optional<Product> op = repo.findById(id);
		
		
		if(op.isPresent()) {
			Product product = op.get();
			return product;
		}else {
		 
			return null;
		}
	}
	

}

