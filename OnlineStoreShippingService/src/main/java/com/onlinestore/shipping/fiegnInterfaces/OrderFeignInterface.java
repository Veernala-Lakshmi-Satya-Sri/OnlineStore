package com.onlinestore.shipping.fiegnInterfaces;

import java.util.List;

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
import com.onlinestore.shipping.dto.ShippingAddress;
import com.onlinestore.shipping.entity.Orders;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;




@CircuitBreaker(name= "external", fallbackMethod = "fallback")
@Retry(name = "myretry")
@FeignClient(name= "ORDER-SERVICE", contextId = "ORDER-DETAILS", configuration = {CustomErrorDecoder.class})
public interface OrderFeignInterface {

	
	@PostMapping("/order")
	public ResponseEntity<Orders> addOrder(@RequestBody Orders o);
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable int id);
	
	
	@PutMapping("/order/{id}")
	public ResponseEntity<Orders> updateOrder(@PathVariable int id, @RequestBody Orders order);
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Orders> searchOrder(@PathVariable int id);
	
	default ResponseEntity<Orders> fallback(ServiceUnavailableException e)  {
		throw new ServiceUnavailableException(e.getMessage());
	}
}
