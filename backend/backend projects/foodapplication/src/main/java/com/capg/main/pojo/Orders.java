package com.capg.main.pojo;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@NotEmpty(message = "Date Should  Not Be Empty!")
//	 @DateTimeFormat(pattern = "dd-mm-yyyy")
	private String orderdate;
	
	
	@ManyToOne(
	cascade = {CascadeType.MERGE})
//	@NotEmpty(message = "Date Should  Not Be Empty!")
//	@NotBlank
	private Food itemid;
	

	@OneToOne(
	cascade = {CascadeType.MERGE})
//	@NotEmpty(message = "Date Should  Not Be Empty!")
//	@NotBlank
	private Customer customerid;
	
	

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Orders(int id, String orderdate, Food itemid, Customer customerid) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.itemid = itemid;
		this.customerid = customerid;
	}



	public int getid() {
		return id;
	}

	public void setOrderid(int id) {
		this.id = id;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public Food getItemid() {
		return itemid;
	}

	public void setItemid(Food itemid) {
		this.itemid = itemid;
	}

	public Customer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Customer customerid) {
		this.customerid = customerid;
	}
	
	
	
	
	
	
	
	
	
}
