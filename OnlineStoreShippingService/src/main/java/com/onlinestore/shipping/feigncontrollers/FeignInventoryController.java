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
import com.onlinestore.shipping.dto.Inventory;
import com.onlinestore.shipping.dto.View.CustomView;
import com.onlinestore.shipping.fiegnInterfaces.InventoryFeignInterface;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shippingservice")
@Tag(name = "3. Inventory APIs")
public class FeignInventoryController {
	
	@Autowired
	InventoryFeignInterface inventoryClient;
	
	@PostMapping("/inventory")
	public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory){
		return inventoryClient.addInventory(inventory);
	}
	

	@DeleteMapping("/inventory/{pid}")
	public ResponseEntity<String> deleteInventory(@PathVariable int pid) {
		return inventoryClient.deleteInventory(pid);
		
	}
	

	@PutMapping("/inventory/{pid}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable int pid,@JsonView(CustomView.PUT.class) @RequestBody Inventory inventory ){
		return inventoryClient.updateInventory(pid, inventory);
	}
	

	@GetMapping("/inventory/{pid}")
	public ResponseEntity<Inventory> searchInventory(@PathVariable int pid){
		return inventoryClient.searchInventory(pid);
	}

}
