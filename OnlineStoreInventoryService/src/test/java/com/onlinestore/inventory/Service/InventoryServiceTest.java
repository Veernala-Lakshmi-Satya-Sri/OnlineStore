package com.onlinestore.inventory.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlinestore.inventory.Exceptions.InventoryAlreadyExistsException;
import com.onlinestore.inventory.Exceptions.ProductNotFoundException;
import com.onlinestore.inventory.model.Inventory;
import com.onlinestore.inventory.repositary.InventoryRepo;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

	@Mock
	InventoryRepo repo;
	
	@InjectMocks
	InventoryService service;

	@Test
	public void addInventoryTest() throws InventoryAlreadyExistsException, ProductNotFoundException {
		Inventory inv= new Inventory(1,11,100);
		
		when(repo.save(inv)).thenReturn(inv);
		Inventory added= service.addInventory(inv);
		
		assertEquals(11, added.getProductId());
		assertEquals(100, added.getQuantity());
	}
	
	@Test
	public void deleteInventory() throws ProductNotFoundException {
		Inventory inv= new Inventory(1,11,100);
		when(repo.findByProductId(11)).thenReturn(inv);
	
		
		String msg= service.deleteInventory(11);
		verify(repo, times(1)).deleteById(anyInt());
		assertEquals("deleted",msg);
		
	}
	
	@Test
	public void searchInventory() throws ProductNotFoundException {
		Inventory inv= new Inventory(1,11,100);
		when(repo.findByProductId(11)).thenReturn(inv);
		Inventory found= service.searchInventory(11);
		
		assertEquals(11, found.getProductId());
		assertEquals(100, found.getQuantity());
		
	}
	
	@Test
	public void updateInventory() throws ProductNotFoundException {
		
		Inventory inv_new= new Inventory(1,11,150);
		Inventory inv= new Inventory(1,11,100);
		when(repo.save(inv)).thenReturn(inv);
		when(repo.findByProductId(11)).thenReturn(inv);
		Inventory found= service.updateInventory(11, inv_new);
		
		assertEquals(150, found.getQuantity());
	}
	
	
}





