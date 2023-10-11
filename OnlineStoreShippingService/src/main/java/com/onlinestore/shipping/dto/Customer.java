package com.onlinestore.shipping.dto;




import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.shipping.dto.View.CustomView;
import com.onlinestore.shipping.dto.View.CustomView.Ignore;
import com.onlinestore.shipping.dto.View.CustomView.PUT;
import com.onlinestore.shipping.entity.Cart;

import feign.Param;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;


public class Customer{
	
	private int customerId;
	
	
	@JsonView({CustomView.Post.class, CustomView.PUT.class})
	private String customerName;
	
	
	@JsonView({CustomView.Post.class, CustomView.PUT.class})
	@Email
	private String customerEmail;
	

	public int getCustomerId() {
		return customerId;
	}




	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}





	public String getCustomerName() {
		return customerName;
	}





	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}





	public String getCustomerEmail() {
		return customerEmail;
	}





	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}






	public Customer() {
		super();
		
		
	}


	public Customer(int customerId, String customerName, String customerEmail) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		
	}


	public List<BiilingAddress> getBillingAddresses() {
		return billingAddresses;
	}





	public void setBillingAddresses(List<BiilingAddress> billingAddresses) {
		this.billingAddresses = billingAddresses;
	}





	public List<ShippingAddress> getShippingAddresses() {
		return shippingAddresses;
	}





	public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}




	public Customer(int customerId, String customerName, String customerEmail,List<BiilingAddress> billingAddresses,List<ShippingAddress> shippingAddresses) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.billingAddresses = billingAddresses;
		this.shippingAddresses = shippingAddresses;
	}



	@JsonView({CustomView.Post.class})
	List<BiilingAddress> billingAddresses;
	
	@JsonView({CustomView.Post.class})
	List<ShippingAddress> shippingAddresses;
}










