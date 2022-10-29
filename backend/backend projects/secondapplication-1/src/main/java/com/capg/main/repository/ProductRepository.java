package com.capg.main.repository;
import org.springframework.data.repository.CrudRepository;

import com.capg.main.pojo.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{

}

