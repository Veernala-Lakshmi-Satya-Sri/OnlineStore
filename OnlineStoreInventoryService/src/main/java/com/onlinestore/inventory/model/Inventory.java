package com.onlinestore.inventory.model;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.inventory.dto.View.InventoryView;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "Inventory")
public class Inventory {
	
	@ReadOnlyProperty
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@SequenceGenerator(initialValue = 10, sequenceName = "inv", name = "inv_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inv_seq")
	private int id;
	
	@JsonView({InventoryView.Post.class})
	private int productId;
	
	
	@JsonView({InventoryView.Post.class,InventoryView.Put.class})
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inventory(int id, int productId, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	
}
