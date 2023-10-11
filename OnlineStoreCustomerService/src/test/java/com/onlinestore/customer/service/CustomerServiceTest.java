package com.onlinestore.customer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlinestore.customer.Exceptions.CustomerNotFoundException;
import com.onlinestore.customer.Exceptions.EmailAlreadyExistsException;
import com.onlinestore.customer.Exceptions.InValidCredintialsException;
import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.entity.ShippingAddress;
import com.onlinestore.customer.repositary.CustomerRepositary;

import jakarta.validation.ConstraintViolationException;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
	
	
	@Mock
	CustomerRepositary customerRepo;
	
	@InjectMocks
	CustomerService customerService;
	
	@Test
	public void addCustomerTest() throws ConstraintViolationException, EmailAlreadyExistsException, InValidCredintialsException {
		
		List<BiilingAddress> a_list= new ArrayList<BiilingAddress>();

		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",12345);
		a_list.add(a1);
		
		List<ShippingAddress> a_list2= new ArrayList<ShippingAddress>();

		ShippingAddress	a2= new ShippingAddress(1,123,"lgb","xyz","Banglore",12345);
		a_list2.add(a2);
		Customer c= new Customer(10,"Satya","satya@gmail.com",a_list,a_list2);
		
		when(customerRepo.save(c)).thenReturn(c);
		
		Customer added= customerService.addCustomer(c);
		
		assertEquals("Satya",added.getCustomerName());
		assertEquals("satya@gmail.com", added.getCustomerEmail());
		assertEquals(c, added);
	}
	
	
	@Test
	public void deleteCustomerTest() throws CustomerNotFoundException {
		
		
		List<BiilingAddress> a_list= new ArrayList<BiilingAddress>();
		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",123456);
		a_list.add(a1);
		
		List<ShippingAddress> a_list2= new ArrayList<ShippingAddress>();

		ShippingAddress	a2= new ShippingAddress(1,123,"lgb","xyz","Banglore",12345);
		a_list2.add(a2);
		Customer c= new Customer(10,"Satya","satya@gmail.com",a_list,a_list2);
		
		when(customerRepo.findById(10))
		.thenReturn(Optional.ofNullable(c))
		.thenReturn(Optional.ofNullable(c))
		.thenReturn(Optional.empty());
		
		
		String msg= customerService.deleteCustomer(10);
		assertEquals("deleted", msg);
		verify(customerRepo, times(1)).deleteById(10);
		
	}
	
	@Test
	public void updateCustomerTest() throws ConstraintViolationException, CustomerNotFoundException, EmailAlreadyExistsException {
		
		List<BiilingAddress> a_list= new ArrayList<BiilingAddress>();
		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",123456);
		a_list.add(a1);
		
		List<ShippingAddress> a_list2= new ArrayList<ShippingAddress>();

		ShippingAddress	a2= new ShippingAddress(1,123,"lgb","xyz","Banglore",12345);
		a_list2.add(a2);
		
		Customer oldCustomer= new Customer(10,"Satya","satya@gmail.com");
		Customer newCustomer= new Customer(10,"New Satya","satya123@gmail.com");
		
		when(customerRepo.findById(10))
		.thenReturn(Optional.ofNullable(oldCustomer))
		.thenReturn(Optional.ofNullable(oldCustomer));

		when(customerRepo.save(oldCustomer)).thenReturn(oldCustomer);
		
		Customer updatedCustomer= customerService.updateCustomer(10,newCustomer);
		
		assertEquals("New Satya",updatedCustomer.getCustomerName());
		assertEquals("satya123@gmail.com", updatedCustomer.getCustomerEmail());
		assertEquals(oldCustomer.getCustomerName(),updatedCustomer.getCustomerName());
		assertEquals(oldCustomer.getCustomerEmail(), updatedCustomer.getCustomerEmail());
		assertEquals(oldCustomer, updatedCustomer);
	}
	

	@Test
	public void searchCustomerTest() throws CustomerNotFoundException {
		
		List<BiilingAddress> a_list= new ArrayList<BiilingAddress>();

		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",123456);
		a_list.add(a1);
		
		List<ShippingAddress> a_list2= new ArrayList<ShippingAddress>();

		ShippingAddress	a2= new ShippingAddress(1,123,"lgb","xyz","Banglore",12345);
		a_list2.add(a2);
		
		Customer c= new Customer(10,"Satya","satya@gmail.com",a_list,a_list2);
		
		when(customerRepo.findById(10)).thenReturn(Optional.ofNullable(c)).thenReturn(Optional.ofNullable(c));
		
		Customer returned= customerService.searchCustomer(10);
		
		assertEquals("Satya",returned.getCustomerName());
		assertEquals("satya@gmail.com", returned.getCustomerEmail());
		assertEquals(c, returned);
	}
	
	

}


