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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.cart.Exceptions.CartNotFoundException;
import com.onlinestore.cart.Exceptions.LineItemNotFoundException;
import com.onlinestore.cart.dto.View.CustomView.PUT;
import com.onlinestore.cart.entity.LineItem;
import com.onlinestore.cart.service.LineItemInterface;


@RestController
@RequestMapping("/cart")
public class LineController {

	
	@Autowired
	LineItemInterface lineItemInterface;
	
	
	@PostMapping("/lineitem/{cartid}")
	public ResponseEntity<LineItem> addLineItem(@PathVariable int cartid,  @RequestBody LineItem lineItem) throws CartNotFoundException {
		return new ResponseEntity<LineItem>(lineItemInterface.addLineItem(cartid,lineItem), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/lineitem/{itemid}")
	public ResponseEntity<String> deleteLineItem(@PathVariable int itemid) throws LineItemNotFoundException  {
		return new ResponseEntity<String>(lineItemInterface.deleteLineItem(itemid), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/lineitems/{cartid}")
	public ResponseEntity<String> deleteAllLineItems(@PathVariable int cartid) throws LineItemNotFoundException, CartNotFoundException  {
		return new ResponseEntity<String>(lineItemInterface.deleteAllLineItems(cartid), HttpStatus.OK);
		
	}
	
	
	
	@PutMapping("/lineitem/{itemid}")
	public ResponseEntity<LineItem> updateLineItem(@PathVariable int itemid,@JsonView(PUT.class) @RequestBody LineItem lineItem) throws LineItemNotFoundException {
		return new ResponseEntity<LineItem>(lineItemInterface.updateLineItem(itemid, lineItem), HttpStatus.OK);
		
	}
	
	@GetMapping("/lineitem/{itemid}")
	public ResponseEntity<LineItem> searchLineItem(@PathVariable int itemid) throws LineItemNotFoundException{
		return new ResponseEntity<LineItem>(lineItemInterface.searchLineItem(itemid), HttpStatus.OK);
		
	}
}
