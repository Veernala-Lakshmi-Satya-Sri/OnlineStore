package com.onlinestore.shipping.feigncontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.onlinestore.shipping.dto.Inventory;
import com.onlinestore.shipping.dto.Product;
import com.onlinestore.shipping.dto.ProductDetails;
import com.onlinestore.shipping.dto.View.CustomView.Post;
import com.onlinestore.shipping.fiegnInterfaces.InventoryFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.ProductFeignInterface;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shippingservice")
@Tag(name= "2. Product APIs")
public class FeignProductController {

	@Autowired
	ProductFeignInterface productFeignClient;
	
	@Autowired
	InventoryFeignInterface inventoryFeignClient;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDetails productDetails){
		
		Product product= new Product();
		product.setProductId(productDetails.getProductId());
		product.setProductName(productDetails.getProductName());
		product.setDescription(productDetails.getDescription());
		product.setPrice(productDetails.getPrice());
		

		ResponseEntity<Product> addedProduct = 	productFeignClient.addProduct(product);
			
		Inventory inv= new Inventory();
		
		inv.setProductId(addedProduct.getBody().getProductId());
		inv.setQuantity(productDetails.getQuantity());
		ResponseEntity<Inventory> addedInventroy=	inventoryFeignClient.addInventory(inv);
		
		
		return new ResponseEntity<Product>(addedProduct.getBody(), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		
		ResponseEntity<String> status= productFeignClient.deleteProduct(id);
		
		inventoryFeignClient.deleteInventory(id);
		
		return status;
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody ProductDetails productDetails)
	{
		
		Product product= new Product();
		
		product.setProductName(productDetails.getProductName());
		product.setDescription(productDetails.getDescription());
		product.setPrice(productDetails.getPrice());
		
		ResponseEntity<Product> updatedProduct = 	productFeignClient.updateProduct(id,product);
			
		//Inventory inv= new Inventory();
		Inventory inv =inventoryFeignClient.searchInventory(id).getBody();
		
		inv.setQuantity(productDetails.getQuantity());
		ResponseEntity<Inventory> updatedInventory=	inventoryFeignClient.updateInventory(id, inv);
		
		
		return updatedProduct;
	
	
	}
	
	
	@GetMapping("/product/{pid}")
	public ResponseEntity<Product> searchProduct(@PathVariable int pid){
		return productFeignClient.searchProduct(pid);
	}
}















