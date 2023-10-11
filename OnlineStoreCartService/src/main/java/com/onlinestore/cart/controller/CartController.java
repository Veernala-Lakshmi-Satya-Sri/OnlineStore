package com.onlinestore.cart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.cart.Exceptions.CartNotFoundException;
import com.onlinestore.cart.entity.Cart;
import com.onlinestore.cart.service.CartInterface;




@RestController
public class CartController {
	
	@Autowired
	CartInterface cartInterface;
	
	
	@PostMapping("/cart")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
		return new ResponseEntity<Cart>(cartInterface.addCart(cart), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<String> deleteCart(@PathVariable int id) throws CartNotFoundException {
		return new ResponseEntity<String>(cartInterface.deleteCart(id), HttpStatus.OK);
		
	}
	
	
	@PutMapping("/cart/{id}")
	public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart) throws CartNotFoundException{
		return new ResponseEntity<Cart>(cartInterface.updateCart(id, cart), HttpStatus.OK);
		
	}
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> searchCart(@PathVariable int id) throws CartNotFoundException {
		return new ResponseEntity<Cart>(cartInterface.searchCart(id), HttpStatus.OK);
		
	}
	
	@PutMapping("/cart/empty/{id}")
	public ResponseEntity<Cart> emptyCart(@PathVariable int id) throws CartNotFoundException{
		return new ResponseEntity<Cart>(cartInterface.emptyCart(id), HttpStatus.OK);
		
	}

}
