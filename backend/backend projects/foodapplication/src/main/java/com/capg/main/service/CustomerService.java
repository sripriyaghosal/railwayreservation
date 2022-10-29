package com.capg.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.main.pojo.Customer;
import com.capg.main.pojo.Food;
import com.capg.main.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
	
	

	
	public Customer saveCustomer(Customer customer) {
		
		return repo.save(customer);
	}
	
	public Optional<Customer> Detail(int id) {
		return repo.findById(id);
	}
	
	public List<Customer> AllCustomer(){
		List<Customer> list = new ArrayList();
		repo.findAll().forEach(list::add);
		return list;
	}

	public String deleteCustomer(int id){
		Optional<Customer> op = repo.findById(id);
		if (op.isPresent()) {
			repo.deleteById(id);
			return "delete";
	}
	return "notDeleted"	;
	}
	
	public Customer updateCustomer(int id, Customer customer) {
		Optional<Customer> op = repo.findById(id);
		if (op.isPresent()) {
			Customer customer2 = op.get();
			customer2.setName(customer.getName());
			customer2.setEmail(customer.getEmail());
			repo.save(customer2);
			return customer2;
		} else {
			return null;
		}
	}
	
}
