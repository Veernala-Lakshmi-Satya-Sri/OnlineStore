package com.onlinestore.inventory.repositary;


import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.inventory.model.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Integer>{

	void deleteByProductId(int id);

	Inventory findByProductId(int id);

	
		
}
