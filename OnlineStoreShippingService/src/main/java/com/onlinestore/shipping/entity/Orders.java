package com.onlinestore.shipping.entity;


import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.onlinestore.shipping.dto.View.CustomView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SequenceGenerator;

//@Entity
//@SecondaryTable(name = "order-b", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cartid"))
public class Orders {
	

//	@Id 
//	@JsonProperty(access = Access.READ_ONLY)
//	@SequenceGenerator(initialValue = 5001, sequenceName = "sorder", name = "sord_seq")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sord_seq")
	private int orderid;
//	private Cart cart;


	
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

//
//	public Cart getCart() {
//		return cart;
//	}
//
//
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}



	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Orders(int id, List<LineItem> lineItems) {
		super();
		this.orderid = id;
		//this.cart = cart;
		this.lineItems = lineItems;
	}

	
	//@JsonView({CustomView.Post.class})
	List<LineItem> lineItems;
	
	
}
	

////@Id 
//@JsonProperty(access = Access.READ_ONLY)
//@SequenceGenerator(initialValue = 300, sequenceName = "order_cart", name = "ocart")
//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ocart")


//@OneToOne(cascade = CascadeType.ALL)
//// @JoinColumn(name = "cart_id_fk", referencedColumnName = "cartid"),
//
//@JoinTable(name = "CUSTOMER-ORDER",
//joinColumns = {
//      @JoinColumn(name = "Order_id_fk", referencedColumnName = "orderid")
//},
//inverseJoinColumns = {
//      @JoinColumn(name = "cart_id_fk", referencedColumnName = "carthgjid", unique = true)
//}
//)	
	
	
	

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

