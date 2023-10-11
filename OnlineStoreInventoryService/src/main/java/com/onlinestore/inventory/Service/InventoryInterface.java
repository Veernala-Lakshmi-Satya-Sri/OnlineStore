package com.onlinestore.inventory.Service;

import org.springframework.stereotype.Service;

import com.onlinestore.inventory.Exceptions.InventoryAlreadyExistsException;
import com.onlinestore.inventory.Exceptions.ProductNotFoundException;
import com.onlinestore.inventory.model.Inventory;

@Service
public interface InventoryInterface {



	Inventory addInventory(Inventory inventory) throws InventoryAlreadyExistsException, ProductNotFoundException;

	String deleteInventory(int id) throws ProductNotFoundException;

	Inventory updateInventory(int pid, Inventory inventory) throws ProductNotFoundException;

	Inventory searchInventory(int pid) throws ProductNotFoundException;

	

}
