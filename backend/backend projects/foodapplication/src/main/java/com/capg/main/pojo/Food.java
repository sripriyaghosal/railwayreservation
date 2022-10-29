package com.capg.main.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
//@Table(name = "food")
@Table(uniqueConstraints= @UniqueConstraint(columnNames={"itemname"}))
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "itemName Should  Not Be Empty!")
	private String itemname;
	 @NotNull
	private int price;
	 
	
	
	public Food()
	{
		super();
	}



	public Food(int id, String itemname, int price) {
		super();
		this.id = id;
		this.itemname = itemname;
		this.price = price;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getItemname() {
		return itemname;
	}



	public void setItemname(String itemname) {
		this.itemname = itemname;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
