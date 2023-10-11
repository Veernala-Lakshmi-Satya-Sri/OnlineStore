package com.onlinestore.shipping.fiegnInterfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlinestore.shipping.Decoder.CustomErrorDecoder;
import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;
import com.onlinestore.shipping.dto.BiilingAddress;
import com.onlinestore.shipping.dto.Customer;
import com.onlinestore.shipping.dto.ShippingAddress;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;



@CircuitBreaker(name= "external", fallbackMethod = "fallback")
@Retry(name = "myretry")
@FeignClient(name="CUSTOMER-SERVICE", contextId = "ADDRESS-DETAILS",configuration = {CustomErrorDecoder.class})
public interface AddressesFeignInterface {
	
	@PostMapping("/address/billing/{cid}")
	public ResponseEntity<Customer> addNewCustomerAddress(@PathVariable int cid,@RequestBody BiilingAddress address);

	@DeleteMapping("/address/billing/{aid}")
	public ResponseEntity<String> deleteCustomerAddress(@PathVariable int aid);
	
	@PutMapping("/address/billing/{aid}")
	public ResponseEntity<BiilingAddress> updateCustomerAddress(@PathVariable int aid,@RequestBody BiilingAddress address );
	
	@GetMapping("/address/billing/{aid}")
	public ResponseEntity<BiilingAddress> searchCustomerAddress(@PathVariable int aid);
	
	
	@PostMapping("/address/shipping/{cid}")
	public ResponseEntity<Customer> addNewCustomerShippingAddress(@PathVariable int cid,@RequestBody ShippingAddress address);
	
	@DeleteMapping("/address/shipping/{aid}")
	public ResponseEntity<String> deleteCustomerShippingAddress(@PathVariable int aid);
	
	
	@PutMapping("/address/shipping/{aid}")
	public ResponseEntity<ShippingAddress> updateCustomerShippingAddress(@PathVariable int aid,@RequestBody ShippingAddress address );
	
	@GetMapping("/address/shipping/{aid}")
	public ResponseEntity<ShippingAddress> searchCustomerShippingAddress(@PathVariable int aid);

	
	default ResponseEntity<ShippingAddress> fallback(ServiceUnavailableException e)  {
		throw new ServiceUnavailableException(e.getMessage());
	}
	

}
