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

import com.onlinestore.shipping.entity.LineItem;
import com.onlinestore.shipping.fiegnInterfaces.OrderLineItemFeignInterface;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/shippingService")
@Tag(name= "5.1 Order LineItem APIs")
public class FeignOrderLineItemController {

	@Autowired
	OrderLineItemFeignInterface feignInterface;
	
	
	@PostMapping("/order/lineitem/{orderid}")
	public ResponseEntity<LineItem> addLineItem(@PathVariable int orderid,@RequestBody LineItem lineItem) {
		return feignInterface.addLineItem(orderid, lineItem);
	}
	

	@DeleteMapping("/order/lineitem/{itemid}")
	public ResponseEntity<String> deleteLineItem(@PathVariable int itemid) {
		return feignInterface.deleteLineItem(itemid);
	}
	

	@PutMapping("/order/lineitem/{itemid}")
	public ResponseEntity<LineItem> updateLineItem(@PathVariable int itemid, @RequestBody LineItem lineItem) {
		return feignInterface.updateLineItem(itemid, lineItem);
	}
	
	@GetMapping("/order/lineitem/{itemid}")
	public ResponseEntity<LineItem> searchLineItem(@PathVariable int itemid) {
		return feignInterface.searchLineItem(itemid);
		
	}
	
}
