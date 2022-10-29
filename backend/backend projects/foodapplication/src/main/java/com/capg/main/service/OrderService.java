package com.capg.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.main.pojo.Customer;
import com.capg.main.pojo.Food;
import com.capg.main.pojo.Orders;
import com.capg.main.repository.CustomerRepository;
import com.capg.main.repository.FoodRepository;
import com.capg.main.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private FoodRepository repo1;
	
	@Autowired
	private CustomerRepository repo2;
	
public Orders saveOrder(Orders order ) {
		
		
		return repo.save(order);
	}

public Customer saveCustomer(Customer c ) {
	
	
	return repo.save(c);
}

public Food saveFood(Food f ) {
	
	
	return  repo.save(f);
}

public Optional<Orders> Detail(int id) {
	return repo.findById(id);
}

public List<Orders> AllOrder(){
	List<Orders> list = new ArrayList();
	repo.findAll().forEach(list::add);
	return list;
}

public Orders updateOrder(int id, Orders orders) {
	Optional<Orders> op = repo.findById(id);
	if (op.isPresent()) {
		Orders order2 = op.get();
		order2.setOrderdate(orders.getOrderdate());
		
//		Optional<Customer> op1 = repo2.findById(orders.getCustomerid().getId());
//		if(op1.isPresent()) {
//			order2.setCustomerid(orders.getCustomerid());
//		}else {
//			return null;
//		}
//		
//		Optional<Food> op2 = repo1.findById(orders.getItemid().getId());
//		if(op2.isPresent()) {
//			order2.setItemid(orders.getItemid());
//		}else {
//			return null;
//		}
		order2.setCustomerid(orders.getCustomerid());
		order2.setItemid(orders.getItemid());
		repo.save(order2);
		return order2;
	} else {
		return null;
	}
}


public String deleteOrder(int id){
	Optional<Orders> op = repo.findById(id);
	if (op.isPresent()) {
		repo.deleteById(id);
		return "delete";
}
return "notDeleted"	;
}

}
