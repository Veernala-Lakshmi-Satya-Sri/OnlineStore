package com.onlinestore.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.customer.Exceptions.CustomerNotFoundException;
import com.onlinestore.customer.Exceptions.EmailAlreadyExistsException;
import com.onlinestore.customer.Exceptions.InValidCredintialsException;
import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.repositary.CustomerRepositary;

import jakarta.validation.ConstraintViolationException;

@Service
public class CustomerService implements CustomerInterface{
	
	
	@Autowired
	CustomerRepositary customerRepo;

	@Override
	public Customer addCustomer(Customer customer) throws ConstraintViolationException, EmailAlreadyExistsException, InValidCredintialsException{
			
			emailCheck(customer);
			return customerRepo.save(customer);
		
		
	}



	@Override
	public String deleteCustomer(int id) throws CustomerNotFoundException  {
		searchCustomer(id);
		
		customerRepo.deleteById(id);
		if(customerRepo.findById(id).isEmpty()) {
			
			return "deleted";	
		}
		
		else {
			return "Not deleted";
		}
		
	}

	@Override
	public Customer searchCustomer(int id) throws CustomerNotFoundException {
		
		if(customerRepo.findById(id).isEmpty()) {
			throw new CustomerNotFoundException("Customer with given ID is not present");
		}
		return customerRepo.findById(id).get();
		
	}

	@Override
	public Customer updateCustomer(int id, Customer newCustomer) throws CustomerNotFoundException, EmailAlreadyExistsException,ConstraintViolationException {
		
		
		Customer c= searchCustomer(id);
		
		emailCheck(newCustomer);
		
		c.setCustomerName(newCustomer.getCustomerName());
		c.setCustomerEmail(newCustomer.getCustomerEmail());
			
	
			
		
		return customerRepo.save(c);
	
	}

	
	public void emailCheck(Customer customer) throws EmailAlreadyExistsException {
		
		
		List<String> emails= new ArrayList<String>();
		
		customerRepo.findAll().forEach(c-> emails.add(c.getCustomerEmail().toLowerCase()));
		if(emails.contains(customer.getCustomerEmail().toLowerCase())) {
			throw new EmailAlreadyExistsException("Email already taken");
		}
	
	}
	
	
}
