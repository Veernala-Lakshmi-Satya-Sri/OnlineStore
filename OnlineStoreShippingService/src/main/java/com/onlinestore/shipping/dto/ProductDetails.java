package com.onlinestore.shipping.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.shipping.dto.View.CustomView;





public class ProductDetails {

	@JsonView({CustomView.Ignore.class})
	private int productId;
	
	@JsonView({CustomView.Post.class, CustomView.PUT.class})
	private String productName;
	
	@JsonView({CustomView.Post.class, CustomView.PUT.class})
	private String description;
	
	@JsonView({CustomView.Post.class, CustomView.PUT.class})
	private float price;
	
	@JsonView({CustomView.Post.class, CustomView.PUT.class})
	private int quantity;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDetails(int productId, String productName, String description, float price, int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	

}
