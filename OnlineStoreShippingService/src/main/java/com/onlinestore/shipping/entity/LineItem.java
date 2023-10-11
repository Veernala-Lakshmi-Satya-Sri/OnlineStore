package com.onlinestore.shipping.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.shipping.dto.View.CustomView;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


public class LineItem {
	
//	
//	@Id
//	@SequenceGenerator(initialValue = 400, sequenceName = "order_line", name = "o_line")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "o_line")
	private int itemId;
	
	@JsonView({CustomView.Post.class})
	private int productId;
	
	@JsonView({CustomView.Post.class})
	private String productName ;
	@JsonView({CustomView.Post.class,CustomView.PUT.class})
	private int quantity;
	
	@JsonView({CustomView.Post.class})
	private int price;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public LineItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LineItem(int productId, String productName, int quantity, int price) {
		super();
	
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}
	public LineItem(int itemId, int productId, String productName, int quantity, int price) {
		super();
		this.itemId = itemId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}
	
	
}
