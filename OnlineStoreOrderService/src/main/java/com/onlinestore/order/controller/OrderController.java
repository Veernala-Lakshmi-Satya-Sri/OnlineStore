package com.onlinestore.order.controller;


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

import com.onlinestore.order.Exceptions.OrderNotFoundException;
import com.onlinestore.order.entity.LineItem;
import com.onlinestore.order.entity.Orders;
import com.onlinestore.order.service.OrderInterface;




@RestController
public class OrderController {
	
	@Autowired
	OrderInterface orderInterface;
	
	
	@PostMapping("/order")
	public ResponseEntity<Orders> addOrder(@RequestBody Orders order){
		


		return new ResponseEntity<Orders>(orderInterface.addOrder(order), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable int id) throws OrderNotFoundException {
		return new ResponseEntity<String>(orderInterface.deleteOrder(id), HttpStatus.OK);
		
	}
	
	
	@PutMapping("/order/{id}")
	public ResponseEntity<Orders> updateOrder(@PathVariable int id, @RequestBody Orders order) throws OrderNotFoundException{
		return new ResponseEntity<Orders>(orderInterface.updateOrder(id, order), HttpStatus.OK);
		
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Orders> searchOrder(@PathVariable int id) throws OrderNotFoundException {
		return new ResponseEntity<Orders>(orderInterface.searchOrder(id), HttpStatus.OK);
		
	}
	

}
