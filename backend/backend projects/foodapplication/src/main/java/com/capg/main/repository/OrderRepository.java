package com.capg.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.main.pojo.Customer;
import com.capg.main.pojo.Food;
import com.capg.main.pojo.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer> {

	Food save(Food order);
	Customer save(Customer order);

}
