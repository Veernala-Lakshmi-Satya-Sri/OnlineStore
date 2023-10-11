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
import com.onlinestore.shipping.entity.LineItem;
import com.onlinestore.shipping.entity.Orders;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@CircuitBreaker(name= "external", fallbackMethod = "fallback")
@Retry(name = "myretry")
@FeignClient(name= "ORDER-SERVICE", contextId = "ORDER-LINEITEM", configuration = {CustomErrorDecoder.class})
public interface OrderLineItemFeignInterface {
	
	
	@PostMapping("/order/lineitem/{orderid}")
	public ResponseEntity<LineItem> addLineItem(@PathVariable int orderid,@RequestBody LineItem lineItem);
	

	@DeleteMapping("/order/lineitem/{itemid}")
	public ResponseEntity<String> deleteLineItem(@PathVariable int itemid);
	

	@PutMapping("/order/lineitem/{itemid}")
	public ResponseEntity<LineItem> updateLineItem(@PathVariable int itemid, @RequestBody LineItem lineItem);
	
	@GetMapping("/order/lineitem/{itemid}")
	public ResponseEntity<LineItem> searchLineItem(@PathVariable int itemid);
	
	
	
	default ResponseEntity<LineItem> fallback(ServiceUnavailableException e)  {
		throw new ServiceUnavailableException(e.getMessage());
	}
}
