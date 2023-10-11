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
import com.onlinestore.shipping.Exceptions.CustomException;
import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;
import com.onlinestore.shipping.entity.Cart;
import com.onlinestore.shipping.entity.LineItem;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;



@CircuitBreaker(name= "external", fallbackMethod = "fallback")
@Retry(name = "myretry")
@FeignClient(name="CART-SERVICE", contextId = "CART-LINEITEM",configuration = {CustomErrorDecoder.class})
public interface CartLineItemFeignInterface {
	
	
	@PostMapping("/cart/lineitem/{cartid}")
	public ResponseEntity<LineItem> addLineItem(@PathVariable int cartid,  @RequestBody LineItem lineItem) ;

	
	@DeleteMapping("/cart//lineitem/{itemid}")
	public ResponseEntity<String> deleteLineItem(@PathVariable int itemid); 
	
	
	
	@PutMapping("/cart//lineitem/{itemid}")
	public ResponseEntity<LineItem> updateLineItem(@PathVariable int itemid, @RequestBody LineItem lineItem); 
	
	
	@GetMapping("/cart//lineitem/{itemid}")
	public ResponseEntity<LineItem> searchLineItem(@PathVariable int itemid);


	
	@DeleteMapping("/cart//lineitems/{cartid}")
	public ResponseEntity<String> deleteAllLineItems(@PathVariable int cartid);

	
	default ResponseEntity<LineItem> fallback(ServiceUnavailableException e)  {
		throw new ServiceUnavailableException(e.getMessage());
	}
}
