package com.onlinestore.customer.entity;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="Billing")
public class BiilingAddress {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@SequenceGenerator(initialValue = 40, sequenceName = "address", name = "billing")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing")
	private int id;
	
	@NotNull
	private int doorNo;
	
	@NotEmpty
	private String streetName;
	
	@NotEmpty
	private String layout;
	
	@NotNull
	private int pin;
	
	@NotEmpty
	private String city;
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(int doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



	
	public BiilingAddress() {
		super();
	}

	public BiilingAddress(int id, int doorNo, String streetName, String layout, String city,int pin) {
		super();
		this.id = id;
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.layout = layout;
		this.pin = pin;
		this.city = city;
	}

	
	
	
	
}
