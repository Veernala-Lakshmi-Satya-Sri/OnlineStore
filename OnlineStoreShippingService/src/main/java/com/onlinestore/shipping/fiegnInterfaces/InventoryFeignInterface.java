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
import com.onlinestore.shipping.Exceptions.CustomException;
import com.onlinestore.shipping.Exceptions.CustomException2;
import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;
import com.onlinestore.shipping.dto.Inventory;
import com.onlinestore.shipping.dto.Product;
import com.onlinestore.shipping.dto.View.CustomView;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@CircuitBreaker(name= "external", fallbackMethod = "fallback")
@Retry(name = "myretry")
@FeignClient(name="INVENTORY-SERVICE",configuration = {CustomErrorDecoder.class})
public interface InventoryFeignInterface {

	@PostMapping("/inventory")
	public ResponseEntity<Inventory> addInventory(@JsonView(CustomView.Post.class) @RequestBody Inventory inventory);
	

	@DeleteMapping("/inventory/{pid}")
	public ResponseEntity<String> deleteInventory(@PathVariable int pid);
	

	@PutMapping("/inventory/{pid}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable int pid,@JsonView(CustomView.PUT.class) @RequestBody Inventory inventory );
	

	@GetMapping("/inventory/{pid}")
	public ResponseEntity<Inventory> searchInventory(@PathVariable int pid);

	default ResponseEntity<Inventory> fallback(ServiceUnavailableException e)  {
		throw new ServiceUnavailableException(e.getMessage());
	}
}
