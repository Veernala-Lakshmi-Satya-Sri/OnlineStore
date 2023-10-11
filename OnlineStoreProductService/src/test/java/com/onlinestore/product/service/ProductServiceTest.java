package com.onlinestore.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlinestore.product.Exceptions.ProductNotFoundException;
import com.onlinestore.product.entity.Product;
import com.onlinestore.product.repositary.ProductRepositary;

@ExtendWith(MockitoExtension.class)

class ProductServiceTest {

	@Mock
	ProductRepositary repo;
	
	@InjectMocks
	ProductService service;


	@Test
	public void addProduct() {
		Product p= new Product(1,"Tv", "Best", 1000);
		when(repo.save(p)).thenReturn(p);
	Product added=	service.addProduct(p);
		
		assertEquals(1, added.getProductId());
		assertEquals("Tv", added.getProductName());
		assertEquals("Best", added.getDescription());
	}
	
	@Test
	public void deleteProduct() throws ProductNotFoundException
	{
		Product p= new Product(1,"Tv", "Best", 1000);
		
		
		when(repo.findById(1)).thenReturn(Optional.ofNullable(p))
		.thenReturn(Optional.ofNullable(p))
		.thenReturn(Optional.empty());
		
		String msg=	service.deleteProduct(1);
		verify(repo, times(1)).deleteById(1);
		assertEquals("deleted",msg);
	
	}
	
	@Test
	public void searchProduct() throws ProductNotFoundException {
		Product p= new Product(1,"Tv", "Best", 1000);
		when(repo.findById(1)).thenReturn(Optional.of(p));
		Product found= service.searchProduct(1);
		assertEquals("Tv", found.getProductName());
		assertEquals("Best", found.getDescription());
	}
	
	@Test
	public void updateProduct() throws ProductNotFoundException {
		Product p= new Product(1,"Tv", "Best", 1000);
		Product p2= new Product(1,"Tv2", "Best2", 10002);
		when(repo.findById(1)).thenReturn(Optional.of(p));
		when(repo.save(p)).thenReturn(p);
		Product update=	service.updateProduct(1,p2);
		
		assertEquals(1, update.getProductId());
		assertEquals("Tv2", update.getProductName());
		assertEquals("Best2", update.getDescription());
	}
	
	
}














