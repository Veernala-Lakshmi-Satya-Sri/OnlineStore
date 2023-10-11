package com.onlinestore.shipping.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.onlinestore.shipping.dto.View.CustomView;
import com.onlinestore.shipping.entity.LineItem;
import com.onlinestore.shipping.entity.Orders;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


public class Cart {
	

//	@Id 
//	@JsonProperty(access = Access.READ_ONLY)
//	@SequenceGenerator(initialValue = 101, sequenceName = "scart", name = "scart_seq")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scart_seq")
	private int cartid;
	
		



	public int getCartid() {
		return cartid;
	}



	public void setCartid(int cartid) {
		this.cartid = cartid;
	}



	public List<LineItem> getLineItems() {
		return lineItems;
	}



	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}



	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Cart(int id, List<LineItem> lineItems) {
		super();
		this.cartid = id;
		this.lineItems = lineItems;
	}

	@JsonView({CustomView.Post.class})
	List<LineItem> lineItems;
}
