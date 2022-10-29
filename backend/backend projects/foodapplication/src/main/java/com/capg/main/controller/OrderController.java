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
import com.capg.main.pojo.Food;
import com.capg.main.pojo.Orders;
import com.capg.main.service.OrderService;


@RestController
@RequestMapping("/order/")
public class OrderController {
	@Autowired
	OrderService oservice;
	
	
//insert
	@PostMapping("save")
	public ResponseEntity<String> saveOrder(@RequestBody  @Valid Orders order) {
		

		Customer cus=oservice.saveCustomer(order.getCustomerid());
		

		Food fo=oservice.saveFood(order.getItemid());
		
		Orders o=oservice.saveOrder(order);
//		System.out.println(order);
//		System.out.println(order.getItemid().getItemname());
//		System.out.println(order.getCustomerid().getName());
		return new ResponseEntity<String>("Order Successfully added", HttpStatus.CREATED);

	}
	
	//getById
	
	@GetMapping("getOrder/{id}")
public ResponseEntity<Object> getOrderDetailsById(@PathVariable("id") int id) {
	
		Optional<Orders> op = oservice.Detail(id);
		if (op.isPresent()) {
			Orders order = op.get();
			return new ResponseEntity<Object>(order, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("order is not present", HttpStatus.NOT_FOUND);
		}
		

	}
	
	//get all data
	
	@GetMapping("allOrder")
	public List<Orders> getAllFood() {
		List<Orders> list = oservice.AllOrder();
		return list;
	}
	
	// update Orders
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<Object>UpdateOrderById(@PathVariable("id") int id, @RequestBody Orders orders)
	{
		 Orders f=oservice.updateOrder(id, orders);
		if(f!=null) {
			return new ResponseEntity<Object>(f, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("NotFound", HttpStatus.NOT_FOUND);
		}
			
		
	}
	
	// delete Orders
	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<String> deleteOrderById(@PathVariable("id") int id) {
		String s=oservice.deleteOrder(id);
		if (s.equals("delete")) {
			
			return new ResponseEntity<String>("Delete successfully", HttpStatus.OK);

					}
		return new ResponseEntity<String>("Order is not present in the list", HttpStatus.NOT_FOUND);


	}
	

	
}
