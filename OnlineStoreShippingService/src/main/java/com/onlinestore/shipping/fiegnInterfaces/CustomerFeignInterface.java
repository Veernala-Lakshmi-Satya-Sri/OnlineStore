package com.onlinestore.shipping.fiegnInterfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.shipping.Decoder.CustomErrorDecoder;
import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;
import com.onlinestore.shipping.dto.Customer;
import com.onlinestore.shipping.dto.View.CustomView.PUT;
import com.onlinestore.shipping.feignConfig.FeignConfig;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;



@CircuitBreaker(name= "external", fallbackMethod = "fallback")
@Retry(name = "myretry")
@FeignClient(name="CUSTOMER-SERVICE",contextId = "CUSTOMER-DETAILS", configuration = {CustomErrorDecoder.class})
public interface CustomerFeignInterface {
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer);
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id);
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id,@RequestBody @JsonView(value=PUT.class) Customer customer );
	
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> searchCustomer(@PathVariable int id);


	
	default ResponseEntity<Customer> fallback(ServiceUnavailableException e)  {
		
		throw new ServiceUnavailableException(e.getMessage());
	}
	
}
 
