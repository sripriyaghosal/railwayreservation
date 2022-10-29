package com.capg.main.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Product {
@Id
@Column(name="pid")
private int id;
@Column(name="pname")
private String name;
@Column(name="price")
private double price;
public Product() {
	super();
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
}

