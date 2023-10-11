package com.onlinestore.customer.service;

import com.onlinestore.customer.Exceptions.AddressNotFoundException;
import com.onlinestore.customer.Exceptions.CustomerNotFoundException;
import com.onlinestore.customer.Exceptions.InValidCredintialsException;
import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.entity.ShippingAddress;

import jakarta.validation.Valid;


public interface AddressInterface {

	Customer addCustomerAddress(int id, @Valid BiilingAddress address) throws CustomerNotFoundException, InValidCredintialsException;

	String deleteCustomerAddress(int aid) throws AddressNotFoundException;

	BiilingAddress searchCustomerAddress(int aid) throws AddressNotFoundException;

	BiilingAddress updateCustomerAddress(int aid,@Valid  BiilingAddress address) throws AddressNotFoundException, InValidCredintialsException;


	Customer addCustomerShippingAddress(int id, @Valid ShippingAddress address) throws CustomerNotFoundException, InValidCredintialsException;

	String deleteCustomerShippingAddress(int aid) throws AddressNotFoundException;

	ShippingAddress searchCustomerShippingAddress(int aid) throws AddressNotFoundException;

	ShippingAddress updateCustomerShippingAddress(int aid,@Valid  ShippingAddress address) throws AddressNotFoundException, InValidCredintialsException;


 
	
}
