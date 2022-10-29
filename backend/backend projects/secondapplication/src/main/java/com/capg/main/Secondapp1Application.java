package com.capg.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Iterator;
import java.util.Optional.*;
import com.capg.main.entity.Product;
import com.capg.main.repository.ProductRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@SpringBootApplication
public class Secondapp1Application implements CommandLineRunner {
@Autowired
ProductRepository pro;
	public static void main(String[] args) {
		SpringApplication.run(Secondapp1Application.class, args);
	}
@Override
public void run(String...args) throws Exception{
	Product product = new Product();
	product.setId(104);
	product.setName("Computer");
	product.setPrice(60000);
	pro.save(product);
System.out.println("Successfully inserted...");
	
	/*java.util.Optional<Product>op = pro.findById(101);
	if(op.isPresent()) {
		System.out.println(op.get());
	}
	else {
		System.out.println("Product is not found.");
	}
	System.out.println("Finding is done...");*/
	
	/*java.util.Optional<Product>op = pro.findById(101);
	if(op.isPresent()) {
		pro.delete(op.get());
	System.out.println("Successfully deleted..");}
	else {
		System.out.println("Product is not found..");
	}*/
	
	/*Iterable<Product> it =pro.findAll();
	Iterator<Product> it1=it.iterator();
	while(it1.hasNext()) {
		System.out.println(it1.next());
	}*/
}
}