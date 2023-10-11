package com.onlinestore.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.product.Exceptions.ProductNotFoundException;
import com.onlinestore.product.entity.Product;
import com.onlinestore.product.service.ProductInterface;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProductController {
	
	@Autowired
	ProductInterface productInterface;
	
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {

		return new ResponseEntity<Product>(productInterface.addProduct(product), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) throws ProductNotFoundException {
		return new ResponseEntity<String>(productInterface.deleteProduct(id), HttpStatus.OK);
		
	}
	
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) throws ProductNotFoundException {
		return new ResponseEntity<Product>(productInterface.updateProduct(id, product), HttpStatus.OK);
		
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> searchProduct(@PathVariable int id) throws ProductNotFoundException {
		return new ResponseEntity<Product>(productInterface.searchProduct(id), HttpStatus.OK);
		
	}
}












