package com.onlinestore.shipping.feigncontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.shipping.dto.Customer;
import com.onlinestore.shipping.dto.View.CustomView;
import com.onlinestore.shipping.dto.View.CustomView.Post;
import com.onlinestore.shipping.entity.Cart;
import com.onlinestore.shipping.feignRepositaries.Customer_Cart_Repo;
import com.onlinestore.shipping.fiegnInterfaces.CartFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.CustomerFeignInterface;
import com.onlinestore.shipping.tables.Customer_Cart;

import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@RequestMapping("/shippingservice")
@Tag(name= "1. Customer APIs")
public class FeignCustomerController {
	
	@Autowired
	CustomerFeignInterface customerFeignClient;
	
	@Autowired
	CartFeignInterface cartFeignClient;
	
	@Autowired
	Customer_Cart_Repo customer_cartRepositary;
	
	
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer){
		

			ResponseEntity<Customer> addedCustomer=customerFeignClient.addCustomer(customer);
			
			Cart c= new Cart();

			
			ResponseEntity<Cart> createdCart= cartFeignClient.addCart(c);
			
			Customer_Cart customer_cart= new Customer_Cart();
			
			customer_cart.setCustomerId(addedCustomer.getBody().getCustomerId());
			customer_cart.setCartId(createdCart.getBody().getCartid());
			customer_cartRepositary.save(customer_cart);
			
			
			return addedCustomer;

	}
	
	@DeleteMapping("customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id){
		
	Customer_Cart cc=	customer_cartRepositary.findByCustomerId(id);
	
		customer_cartRepositary.deleteById(cc.getCustomerId());
		return customerFeignClient.deleteCustomer(id);
		
		
	}
	
	@PutMapping("customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id,
			@RequestBody @JsonView(value=CustomView.PUT.class) Customer customer ){
		return customerFeignClient.updateCustomer(id, customer);
	}

	@GetMapping("customer/{id}")
	public ResponseEntity<Customer> searchCustomer(@PathVariable int id){
		return customerFeignClient.searchCustomer(id);
	}

}









