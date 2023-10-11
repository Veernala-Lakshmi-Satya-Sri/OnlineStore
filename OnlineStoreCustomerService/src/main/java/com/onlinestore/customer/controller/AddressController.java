package com.onlinestore.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.customer.Exceptions.AddressNotFoundException;
import com.onlinestore.customer.Exceptions.CustomerNotFoundException;
import com.onlinestore.customer.Exceptions.InValidCredintialsException;
import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.entity.ShippingAddress;
import com.onlinestore.customer.service.AddressInterface;




@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressInterface addressInterface;
	

	@PostMapping("/billing/{cid}")
	public ResponseEntity<Customer> addNewCustomerAddress(@PathVariable int cid,@RequestBody BiilingAddress address) throws CustomerNotFoundException, InValidCredintialsException {
		
			return new ResponseEntity<Customer>(addressInterface.addCustomerAddress(cid,address), HttpStatus.OK);

	}
	
	
	@DeleteMapping("/billing/{aid}")
	public ResponseEntity<String> deleteCustomerAddress(@PathVariable int aid) throws AddressNotFoundException {
		
			return new ResponseEntity<String>(addressInterface.deleteCustomerAddress(aid), HttpStatus.OK);

	}
	
	@PutMapping("/billing/{aid}")
	public ResponseEntity<BiilingAddress> updateCustomerAddress(@PathVariable int aid,@RequestBody BiilingAddress address ) throws AddressNotFoundException, InValidCredintialsException
	{
		
			return new ResponseEntity<BiilingAddress>(addressInterface.updateCustomerAddress(aid, address), HttpStatus.OK);

	}

	@GetMapping("/billing/{aid}")
	public ResponseEntity<BiilingAddress> searchCustomerAddress(@PathVariable int aid) throws AddressNotFoundException {
		
			return new ResponseEntity<BiilingAddress>(addressInterface.searchCustomerAddress(aid), HttpStatus.OK);

	}
	
	@PostMapping("/shipping/{cid}")
	public ResponseEntity<Customer> addNewCustomerShippingAddress(@PathVariable int cid,@RequestBody ShippingAddress address) throws CustomerNotFoundException, InValidCredintialsException {
		
			return new ResponseEntity<Customer>(addressInterface.addCustomerShippingAddress(cid,address), HttpStatus.OK);

	}
	
	
	@DeleteMapping("/shipping/{aid}")
	public ResponseEntity<String> deleteCustomerShippingAddress(@PathVariable int aid) throws AddressNotFoundException {
		
			return new ResponseEntity<String>(addressInterface.deleteCustomerShippingAddress(aid), HttpStatus.OK);

	}
	
	@PutMapping("/shipping/{aid}")
	public ResponseEntity<ShippingAddress> updateCustomerShippingAddress(@PathVariable int aid,@RequestBody ShippingAddress address ) throws AddressNotFoundException, InValidCredintialsException
	{
		
			return new ResponseEntity<ShippingAddress>(addressInterface.updateCustomerShippingAddress(aid, address), HttpStatus.OK);

	}

	@GetMapping("/shipping/{aid}")
	public ResponseEntity<ShippingAddress> searchCustomerShippingAddress(@PathVariable int aid) throws AddressNotFoundException {
		
			return new ResponseEntity<ShippingAddress>(addressInterface.searchCustomerShippingAddress(aid), HttpStatus.OK);

	}
	
	

}
