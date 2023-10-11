package com.onlinestore.cart.entity;


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
public class Cart {

	//@ReadOnlyProperty
	@Id 
	@JsonProperty(access = Access.READ_ONLY)
	@SequenceGenerator(initialValue = 101, sequenceName = "c", name = "cart_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
	//@GeneratedValue
	private int cartid;
	



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




	public int getCartid() {
		return cartid;
	}



	public void setCartid(int cartid) {
		this.cartid = cartid;
	}




	public Cart(int cartId, List<LineItem> lineItems) {
		super();
		this.cartid = cartId;
		this.lineItems = lineItems;
	}
	


	@OneToMany(targetEntity = LineItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id", referencedColumnName = "cartid")
	List<LineItem> lineItems;
}
