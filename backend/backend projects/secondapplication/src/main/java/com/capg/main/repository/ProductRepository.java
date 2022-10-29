package com.capg.main.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.main.entity.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>{
}
