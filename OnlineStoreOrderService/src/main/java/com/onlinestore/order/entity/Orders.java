package com.onlinestore.order.entity;


import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Orders {
	

//@ReadOnlyProperty
	@Id 
	@JsonProperty(access = Access.READ_ONLY)
	@SequenceGenerator(initialValue = 5001, sequenceName = "o", name = "ord_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_seq")
	private int orderid;
	


		


	
	public int getOrderid() {
		return orderid;
	}



	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}


	public List<LineItem> getLineItems() {
		return lineItems;
	}



	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}


	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Orders(int id, List<LineItem> lineItems) {
		super();
		this.orderid = id;
		this.lineItems = lineItems;
	}

	
	@OneToMany(targetEntity = LineItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name="order_id_fk", referencedColumnName = "orderid")
	List<LineItem> lineItems;
	
	
}
	
	
	
	
	

//
//	public List<LineItem> getLineItems() {
//		return lineItems;
//	}
//
//
//
//	public void setLineItems(List<LineItem> lineItems) {
//		this.lineItems = lineItems;
//	}
//
//
//
//	public Orders() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//
//
//	public Orders(int id, List<LineItem> lineItems) {
//		super();
//		this.id = id;
//		this.lineItems = lineItems;
//	}


//	@OneToMany(targetEntity = LineItem.class, cascade = CascadeType.ALL)
//	@JoinColumn(name="order_id", referencedColumnName = "id")
//	List<LineItem> lineItems;

