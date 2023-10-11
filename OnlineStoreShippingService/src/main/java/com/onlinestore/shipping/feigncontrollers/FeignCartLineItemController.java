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
import com.onlinestore.shipping.Exceptions.InSufficientQuantity;
import com.onlinestore.shipping.dto.Inventory;
import com.onlinestore.shipping.dto.View.CustomView.PUT;
import com.onlinestore.shipping.entity.LineItem;
import com.onlinestore.shipping.fiegnInterfaces.CartLineItemFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.InventoryFeignInterface;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shippingservice")
@Tag(name= "4.1 Cart Line Items APIs")
public class FeignCartLineItemController {
	
	@Autowired
	CartLineItemFeignInterface cartLineItemFeignInterface;
	
	
	@Autowired 
	InventoryFeignInterface inventory;

	@PostMapping("/lineitem/{cartid}")
	public ResponseEntity<LineItem> addLineItem(@PathVariable int cartid,  @RequestBody LineItem lineItem) {
		
		Inventory inv= inventory.searchInventory(lineItem.getProductId()).getBody();
		if(lineItem.getQuantity()> inv.getQuantity()) {
			throw new InSufficientQuantity("Maximum quantity of the product is "+ inv.getQuantity());
		}
		
		return cartLineItemFeignInterface.addLineItem(cartid,lineItem);
		
	}
	
	@DeleteMapping("/lineitem/{itemid}")
	public ResponseEntity<String> deleteLineItem(@PathVariable int itemid) {
		return cartLineItemFeignInterface.deleteLineItem(itemid);
		
	}
	@DeleteMapping("/lineitems/{cartid}")
	public ResponseEntity<String> deleteAllLineItems(@PathVariable int cartid) {
		return cartLineItemFeignInterface.deleteAllLineItems(cartid);
		
	}
	
	
	@PutMapping("/lineitem/{itemid}")
	public ResponseEntity<LineItem> updateLineItem(@PathVariable int itemid,@JsonView(PUT.class) @RequestBody LineItem lineItem){
	int pId=cartLineItemFeignInterface.searchLineItem(itemid).getBody().getProductId();
	System.out.println("pId");
		int qty= inventory.searchInventory(pId).getBody().getQuantity();
		if(qty <lineItem.getQuantity()) {
			throw new InSufficientQuantity("Maximum quantity of the product "+pId+" is "+ qty);
		}
		return cartLineItemFeignInterface.updateLineItem(itemid, lineItem);
		
	}
	
	@GetMapping("/lineitem/{itemid}")
	public ResponseEntity<LineItem> searchLineItem(@PathVariable int itemid){
		return cartLineItemFeignInterface.searchLineItem(itemid);
		
	}
}
