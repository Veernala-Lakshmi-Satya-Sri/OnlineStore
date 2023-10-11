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
import com.onlinestore.shipping.dto.BiilingAddress;
import com.onlinestore.shipping.dto.Customer;
import com.onlinestore.shipping.dto.ShippingAddress;
import com.onlinestore.shipping.dto.View.CustomView.Post;
import com.onlinestore.shipping.fiegnInterfaces.AddressesFeignInterface;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shippingservice")
@Tag(name= "Customer Address APIs")
public class FeignAddressController {

	
	@Autowired
	AddressesFeignInterface addressFeignClient;
	
	@PostMapping("/address/billing/{cid}")
	public ResponseEntity<Customer> addNewCustomerAddress(@PathVariable int cid,@RequestBody BiilingAddress address) {
		return addressFeignClient.addNewCustomerAddress(cid, address);
	}
	
	@DeleteMapping("/address/billing/{aid}")
	public ResponseEntity<String> deleteCustomerAddress(@PathVariable int aid) {
		return addressFeignClient.deleteCustomerAddress(aid);
	}
	
	@PutMapping("/address/billing/{aid}")
	public ResponseEntity<BiilingAddress> updateCustomerAddress(@PathVariable int aid,@RequestBody BiilingAddress address ){
	return addressFeignClient.updateCustomerAddress(aid, address);
		
	}
	

	@GetMapping("/address/billing/{aid}")
	public ResponseEntity<BiilingAddress> searchCustomerAddress(@PathVariable int aid){
		
		
		System.out.println(addressFeignClient.searchCustomerAddress(aid).getBody().getStreetName());
		
		return addressFeignClient.searchCustomerAddress(aid);
	}
	
	
	@PostMapping("/address/shipping/{cid}")
	public ResponseEntity<Customer> addNewCustomerShippingAddress(@PathVariable int cid, @JsonView(Post.class) @RequestBody ShippingAddress address){
		return addressFeignClient.addNewCustomerShippingAddress(cid, address);
	}
	
	@DeleteMapping("/address/shipping/{aid}")
	public ResponseEntity<String> deleteCustomerShippingAddress(@PathVariable int aid) {
		return addressFeignClient.deleteCustomerShippingAddress(aid);
	}
	
	
	@PutMapping("/address/shipping/{aid}")
	public ResponseEntity<ShippingAddress> updateCustomerShippingAddress(@PathVariable int aid,@RequestBody ShippingAddress address )
	{
		return addressFeignClient.updateCustomerShippingAddress(aid, address);
	}
	
	@GetMapping("/address/shipping/{aid}")
	public ResponseEntity<ShippingAddress> searchCustomerShippingAddress(@PathVariable int aid){
		return addressFeignClient.searchCustomerShippingAddress(aid);
	}
}













