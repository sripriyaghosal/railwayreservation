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

import com.capg.main.pojo.Customer;
import com.capg.main.service.CustomerService;

@RestController
@RequestMapping("/customer/")
public class CustomerController {
	
	@Autowired
	CustomerService Cservice;
	//Retrive data from table
		@GetMapping("getCustomer/{itemid}")
		
		public ResponseEntity<Object> getProductDetails(@PathVariable("id") int id) {
			Optional<Customer> op = Cservice.Detail(id);
			if (op.isPresent()) {
				Customer customer = op.get();
				return new ResponseEntity<Object>(customer, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Item is not present", HttpStatus.NOT_FOUND);
			}

		}

		

		// Insert item in Table Customer
		@PostMapping("/save")
		public ResponseEntity<Customer> saveFood(@RequestBody  @Valid Customer customer) {
			
		Customer u=	Cservice.saveCustomer(customer);
			return new ResponseEntity<Customer>(u, HttpStatus.CREATED);

		}
		
		// Get all Customer item
			@GetMapping("/allCustomer")
			
			public List<Customer> getAllCustomer() {
				List<Customer> list = Cservice.AllCustomer();
				return list;
			}
		
			// Delete Customer by id
			@DeleteMapping("/deleteCustomer/{id}")
			public ResponseEntity<String> deleteFoodById(@PathVariable("id") int id) {
				String s=Cservice.deleteCustomer(id);
				if (s.equals("delete")) {
					
					return new ResponseEntity<String>("Delete successfully", HttpStatus.OK);

							}
				return new ResponseEntity<String>("Customer is not present in the list", HttpStatus.NOT_FOUND);


			}

			// Update Customer item by ID
			@PutMapping("/updateCustomer/{id}")
			public ResponseEntity<Object> UpdateFoodById(@PathVariable("id") int id, @RequestBody Customer customer) {
				Customer f=Cservice.updateCustomer(id, customer);
				if(f!=null) {
					return new ResponseEntity<Object>(f, HttpStatus.OK);
				} else {
					return new ResponseEntity<Object>("NotFound", HttpStatus.NOT_FOUND);
				}

			}

			
	

}

