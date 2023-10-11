package com.onlinestore.shipping.feigncontrollers;


import java.util.List;


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

import com.onlinestore.shipping.Exceptions.CustomException;
import com.onlinestore.shipping.Exceptions.InSufficientQuantity;
import com.onlinestore.shipping.dto.Customer;
import com.onlinestore.shipping.entity.Cart;
import com.onlinestore.shipping.entity.LineItem;
import com.onlinestore.shipping.feignRepositaries.Customer_Cart_Repo;
import com.onlinestore.shipping.fiegnInterfaces.CartFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.CustomerFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.InventoryFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.ProductFeignInterface;
import com.onlinestore.shipping.tables.Customer_Cart;

import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/shippingservice")
@Tag(name= "4. Cart APIs")
public class FeignCartController {

	@Autowired
	CartFeignInterface cartFeignInterface;
	
	@Autowired
	CustomerFeignInterface customerFeignInterface;
	
	@Autowired
	ProductFeignInterface productFeignInterface;
	
	@Autowired
	Customer_Cart_Repo	customer_cartRepositary;
	
	@Autowired
	InventoryFeignInterface inventory;
	
	
	
	//called only through when customer creation
	@PostMapping("/cart")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
		
		List<LineItem> items = cart.getLineItems();
		for(LineItem i: items) {
			
		productFeignInterface.searchProduct(i.getProductId()).getBody();
			int qty=inventory.searchInventory(i.getProductId()).getBody().getQuantity();
			
			if(qty < i.getQuantity()) {
				throw new InSufficientQuantity("Maximum Quantity of the product with ID "+ i.getProductId()+ " is "+ qty);
			}
	
		
	}
		return cartFeignInterface.addCart(cart);
		
		
	}
	
	//called only through when customer creation
	@DeleteMapping("/cart/{cartid}")
	public ResponseEntity<String> deleteCart(@PathVariable int cartid) throws CustomException
	{
		Customer_Cart cc=	customer_cartRepositary.findByCartId(cartid);
		if(cc!= null) {
			customer_cartRepositary.deleteById(cc.getId());
			
		}
		else {
			throw new CustomException("Cart with Given Id is not present");
		}
		return cartFeignInterface.deleteCart(cartid);
	}
	
	
	@PutMapping("/cart/{customerid}")
	public ResponseEntity<Cart> updateCart(@PathVariable int customerid, @RequestBody Cart cart) 
	{
		
		customerFeignInterface.searchCustomer(customerid);
		
		Customer_Cart cc=customer_cartRepositary.findByCustomerId(customerid);

		int cart_id= cc.getCartId();
		
		List<LineItem> items = cart.getLineItems();
		for(LineItem i: items) {
			
			productFeignInterface.searchProduct(i.getProductId());/// to throw exception
			
			productFeignInterface.searchProduct(i.getProductId()).getBody();
			int qty=inventory.searchInventory(i.getProductId()).getBody().getQuantity();
			
			
				if(qty < i.getQuantity()) {
					
					throw new InSufficientQuantity("Maximum Quantity of the product with ID "+ i.getProductId()+ " is "+ qty);
				}	
		}
		
		return cartFeignInterface.updateCart(cart_id, cart);
		
	}
	
	@GetMapping("/cart/{customerid}")
	public ResponseEntity<Cart> searchCart(@PathVariable int customerid){
		
		ResponseEntity<Customer> c= customerFeignInterface.searchCustomer(customerid);
		
		
		Customer_Cart cc=customer_cartRepositary.findByCustomerId(customerid);
		
		int cart_id= cc.getCartId();
		
		
		return cartFeignInterface.searchCart(cart_id);
		
	}
	
	@PutMapping("/cart/empty/{id}")
	public ResponseEntity<Cart> emptyCart(@PathVariable int id){
		return cartFeignInterface.emptyCart(id);
	}
}
