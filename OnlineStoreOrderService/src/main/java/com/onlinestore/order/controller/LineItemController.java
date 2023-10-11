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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.order.Exceptions.LineItemNotFoundException;
import com.onlinestore.order.Exceptions.OrderNotFoundException;
import com.onlinestore.order.entity.LineItem;
import com.onlinestore.order.service.LineItemInterface;


@RestController
@RequestMapping("/order")
public class LineItemController {

	
	@Autowired
	LineItemInterface lineItemInterface;
	
	
	@PostMapping("/lineitem/{orderid}")
	public ResponseEntity<LineItem> addLineItem(@PathVariable int orderid,@RequestBody LineItem lineItem) throws OrderNotFoundException {
		return new ResponseEntity<LineItem>(lineItemInterface.addLineItem(orderid,lineItem), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/lineitem/{itemid}")
	public ResponseEntity<String> deleteLineItem(@PathVariable int itemid) throws LineItemNotFoundException  {
		return new ResponseEntity<String>(lineItemInterface.deleteLineItem(itemid), HttpStatus.OK);
		
	}
	
	
	@PutMapping("/lineitem/{itemid}")
	public ResponseEntity<LineItem> updateLineItem(@PathVariable int itemid, @RequestBody LineItem lineItem) throws LineItemNotFoundException {
		return new ResponseEntity<LineItem>(lineItemInterface.updateLineItem(itemid, lineItem), HttpStatus.OK);
		
	}
	
	@GetMapping("/lineitem/{itemid}")
	public ResponseEntity<LineItem> searchLineItem(@PathVariable int itemid) throws LineItemNotFoundException{
		return new ResponseEntity<LineItem>(lineItemInterface.searchLineItem(itemid), HttpStatus.OK);
		
	}
}
