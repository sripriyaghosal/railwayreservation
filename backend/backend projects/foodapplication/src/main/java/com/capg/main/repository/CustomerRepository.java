package com.capg.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.main.pojo.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
