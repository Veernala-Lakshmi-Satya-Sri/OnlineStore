package com.onlinestore.customer.service;

import org.springframework.stereotype.Service;

import com.onlinestore.customer.Exceptions.CustomerNotFoundException;
import com.onlinestore.customer.Exceptions.EmailAlreadyExistsException;
import com.onlinestore.customer.Exceptions.InValidCredintialsException;
import com.onlinestore.customer.entity.Customer;

import jakarta.validation.ConstraintViolationException;

@Service
public interface CustomerInterface {

	Customer addCustomer(Customer customer) throws ConstraintViolationException, EmailAlreadyExistsException, InValidCredintialsException;

	String deleteCustomer(int id) throws CustomerNotFoundException;

	Customer searchCustomer(int id) throws CustomerNotFoundException;

	Customer updateCustomer(int id, Customer newCustomer) throws CustomerNotFoundException, EmailAlreadyExistsException;

}
