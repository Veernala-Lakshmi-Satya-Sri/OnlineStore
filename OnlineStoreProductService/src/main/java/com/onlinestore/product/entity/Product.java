package com.onlinestore.product.entity;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Product {
	
//	@JsonProperty(access= JsonProperty.Access.READ_ONLY)
	@Id
	@SequenceGenerator(initialValue = 200, sequenceName = "productseq", name = "prod")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod")
//	@GeneratedValue
	@ReadOnlyProperty
	private int productId;
	
	private String productName;
	
	private String description;
	
	private float price;

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

	public Product() {
		super();
	}

	public Product(int productId, String productName, String description, float price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
	}
	
	
	
}
