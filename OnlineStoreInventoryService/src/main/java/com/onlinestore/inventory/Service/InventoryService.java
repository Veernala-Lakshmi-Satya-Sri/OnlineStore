package com.onlinestore.inventory.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.inventory.Exceptions.InventoryAlreadyExistsException;
import com.onlinestore.inventory.Exceptions.ProductNotFoundException;
import com.onlinestore.inventory.model.Inventory;
import com.onlinestore.inventory.repositary.InventoryRepo;

@Service
public class InventoryService implements InventoryInterface{

	@Autowired
	InventoryRepo inventoryRepo;

	@Override
	public Inventory addInventory(Inventory inventory) throws InventoryAlreadyExistsException, ProductNotFoundException {

		return inventoryRepo.save(inventory);
	}

	@Override
	public String deleteInventory(int id) throws ProductNotFoundException  {
	Inventory inv= 	searchInventory(id);
		//Inventory inv= inventoryRepo.findByProductId(id);
	int inv_id= inv.getId();
		inventoryRepo.deleteById(inv_id);
		if(inventoryRepo.findById(inv.getId()).isEmpty()) {
			return "deleted";
		}
		else {
			return "not deleted";
		}
		 
	}

	@Override
	public Inventory updateInventory(int pid, Inventory inventory) throws ProductNotFoundException {
		
		Inventory inv=searchInventory(pid);
		inv.setQuantity(inventory.getQuantity());
		return inventoryRepo.save(inv);
	}

	@Override
	public Inventory searchInventory(int pid) throws ProductNotFoundException {
		Inventory inv= inventoryRepo.findByProductId(pid);
		if(inv == null) {
			throw new ProductNotFoundException("Inventory with given productId" + pid + " not exists");
		}
		else {
			return inv;
		}
	}
		
}
