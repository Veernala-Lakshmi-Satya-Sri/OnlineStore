package com.onlinestore.shipping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	
	@GetMapping("/orderServiceFallback")
	public String orderServiceFallback() {
		return "Order service is down";
	}
	
	@GetMapping("/shippingServiceFallback")
	public String shippingServiceFallback() {
		return "Shipping service is down";
	}
	
	@GetMapping("/productServiceFallback")
	public String productServiceFallback() {
		return "Product service is down";
	}
	
	@GetMapping("/cartServiceFallback")
	public String cartServiceFallback() {
		return "Cart service is down";
	}
	
	@GetMapping("/inventoryServiceFallback")
	public String inServiceFallback() {
		return "Inventory service is down";
	}
	
	@GetMapping("/customerServiceFallback")
	public String OrderServiceFallback() {
		return "Customer service is down";
	}
	
}
