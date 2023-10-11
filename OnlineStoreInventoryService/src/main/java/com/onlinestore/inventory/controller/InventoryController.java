package com.onlinestore.inventory.controller;

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

import com.fasterxml.jackson.annotation.JsonView;
import com.onlinestore.inventory.Exceptions.InventoryAlreadyExistsException;
import com.onlinestore.inventory.Exceptions.ProductNotFoundException;
import com.onlinestore.inventory.Service.InventoryInterface;
import com.onlinestore.inventory.dto.View.InventoryView;
import com.onlinestore.inventory.model.Inventory;



@RestController
public class InventoryController {
	
	
	@Autowired
	private InventoryInterface inventoryInterface;
	

	@PostMapping("/inventory")
	public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) throws InventoryAlreadyExistsException, ProductNotFoundException{
		
			return new ResponseEntity<Inventory>(inventoryInterface.addInventory(inventory), HttpStatus.CREATED);

	}
	
	
	@DeleteMapping("/inventory/{pid}")
	public ResponseEntity<String> deleteInventory(@PathVariable int pid) throws ProductNotFoundException {
		
			return new ResponseEntity<String>(inventoryInterface.deleteInventory(pid), HttpStatus.OK);

	}
	
	@PutMapping("/inventory/{pid}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable int pid,@JsonView(InventoryView.Put.class) @RequestBody Inventory inventory ) throws ProductNotFoundException
	{
		
			return new ResponseEntity<Inventory>(inventoryInterface.updateInventory(pid, inventory), HttpStatus.OK);

	}
	

	@GetMapping("/inventory/{pid}")
	public ResponseEntity<Inventory> searchInventory(@PathVariable int pid) throws ProductNotFoundException {
		
			return new ResponseEntity<Inventory>(inventoryInterface.searchInventory(pid), HttpStatus.OK);

	}
}



