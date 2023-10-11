package com.onlinestore.shipping.fiegnInterfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlinestore.shipping.Decoder.CustomErrorDecoder;
import com.onlinestore.shipping.Exceptions.CustomException;
import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;
import com.onlinestore.shipping.dto.Product;
import com.onlinestore.shipping.entity.Cart;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;





@CircuitBreaker(name= "external", fallbackMethod = "fallback")
@Retry(name = "myretry")
@FeignClient(name= "CART-SERVICE", contextId = "CART-DETAILS",configuration = {CustomErrorDecoder.class})
public interface CartFeignInterface {

	
	@PostMapping("/cart")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart); 

	@DeleteMapping("/cart/{id}")
	public ResponseEntity<String> deleteCart(@PathVariable int id);


	
	@PutMapping("/cart/{id}")
	public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart);

	
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> searchCart(@PathVariable int id);

	@PutMapping("/cart/empty/{id}")
	public ResponseEntity<Cart> emptyCart(@PathVariable int id);
	
	
	default ResponseEntity<Cart> fallback(ServiceUnavailableException e)  {
		throw new ServiceUnavailableException(e.getMessage());
	}
}
