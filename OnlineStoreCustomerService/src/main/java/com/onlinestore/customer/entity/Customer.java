package com.onlinestore.customer.entity;




import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.customer.dto.View.CustomerView;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;


@Entity
//@Table(name= "Customer")
public class Customer {
	
	//@ReadOnlyProperty
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@SequenceGenerator(initialValue = 1001, sequenceName = "cus", name = "cus_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cus_seq")
	private int customerId;
	
	
	@JsonView({CustomerView.Post.class, CustomerView.PUT.class})
	private String customerName;
	
	
	@JsonView({CustomerView.Post.class, CustomerView.PUT.class})
	@Email
	private String customerEmail;
	
	
	
	
	
	
	public int getCustomerId() {
		return customerId;
	}




	//@JsonProperty
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



	@JsonView({CustomerView.Post.class})
	@OneToMany(targetEntity = BiilingAddress.class, cascade = CascadeType.ALL)
	@JoinColumn(name="customerId_fk", referencedColumnName = "customerId")
	List<BiilingAddress> billingAddresses;
	
	@JsonView({CustomerView.Post.class})
	@OneToMany(targetEntity = ShippingAddress.class, cascade = CascadeType.ALL)
	@JoinColumn(name="customerId_fk", referencedColumnName = "customerId")
	List<ShippingAddress> shippingAddresses;
}










