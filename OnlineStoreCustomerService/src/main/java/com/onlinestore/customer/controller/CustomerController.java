package com.onlinestore.customer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.customer.Exceptions.CustomerNotFoundException;
import com.onlinestore.customer.Exceptions.EmailAlreadyExistsException;
import com.onlinestore.customer.Exceptions.InValidCredintialsException;
import com.onlinestore.customer.dto.View.CustomerView;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.service.CustomerInterface;

import jakarta.validation.ConstraintViolationException;


@RestController
public class CustomerController {
	
	
	@Autowired
	private CustomerInterface customerInterface;

	

	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws ConstraintViolationException, EmailAlreadyExistsException, InValidCredintialsException{
		
			return new ResponseEntity<Customer>(customerInterface.addCustomer(customer), HttpStatus.CREATED);

	}
	
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id) throws CustomerNotFoundException{
		
			return new ResponseEntity<String>(customerInterface.deleteCustomer(id), HttpStatus.OK);

	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id,@RequestBody @JsonView(value=CustomerView.PUT.class) Customer customer ) throws CustomerNotFoundException, EmailAlreadyExistsException
	{
		
			return new ResponseEntity<Customer>(customerInterface.updateCustomer(id, customer), HttpStatus.OK);

	}
	

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> searchCustomer(@PathVariable int id) throws CustomerNotFoundException{
		
			return new ResponseEntity<Customer>(customerInterface.searchCustomer(id), HttpStatus.OK);

	}

}
