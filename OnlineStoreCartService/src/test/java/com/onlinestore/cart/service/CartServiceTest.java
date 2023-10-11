package com.onlinestore.cart.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlinestore.cart.Exceptions.CartNotFoundException;
import com.onlinestore.cart.entity.Cart;
import com.onlinestore.cart.entity.LineItem;
import com.onlinestore.cart.repositary.CartRepositary;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

	@Mock
	CartRepositary repo;
	
	@InjectMocks
	CartService service;
	
	@Test
	public void addCartTest() {
		List<LineItem> items= new ArrayList<LineItem>();
		
		items.add(new LineItem(1,10,"Laptop", 2, 40000));
		Cart cart= new Cart(101, items);
		when(repo.save(cart)).thenReturn(cart);
		
		Cart added= service.addCart(cart);
		assertEquals(101, added.getCartid());
		assertEquals(1, added.getLineItems().get(0).getItemId());
		assertEquals(10, added.getLineItems().get(0).getProductId());
		assertEquals("Laptop", added.getLineItems().get(0).getProductName());
		assertEquals(40000, added.getLineItems().get(0).getPrice());
	}
	
	@Test
	public void deleteCartTest() throws CartNotFoundException {
		List<LineItem> items= new ArrayList<LineItem>();
		
		items.add(new LineItem(1,10,"Laptop", 2, 40000));
		Cart cart= new Cart(101, items);
		
		when(repo.findById(anyInt()))
		.thenReturn(Optional.ofNullable(cart))
		.thenReturn(Optional.ofNullable(cart))
		.thenReturn(Optional.empty());
		
		String msg=	service.deleteCart(101);
		verify(repo, times(1)).deleteById(101);
		assertEquals("deleted", msg);
		
	}
	
	@Test
	public void updateCartTest() throws CartNotFoundException {
		
		
		List<LineItem> items= new ArrayList<LineItem>();
		items.add(new LineItem(1,10,"Laptop", 2, 40000));
		Cart cart= new Cart(101, items);
		
		
		List<LineItem> items2= new ArrayList<LineItem>();
		LineItem i= new LineItem(2,11,"TV", 1, 25000);
		items2.add(i);
		Cart cart2= new Cart(101,items2);
		
		
		when(repo.save(cart)).thenReturn(cart);
		
		when(repo.findById(anyInt()))
		.thenReturn(Optional.ofNullable(cart))
		.thenReturn(Optional.ofNullable(cart));
	
		
		Cart updated= service.updateCart(101, cart2);
		assertEquals(101, updated.getCartid());
		assertEquals(2, updated.getLineItems().size());
		
		assertEquals(1, updated.getLineItems().get(0).getItemId());
		assertEquals(10, updated.getLineItems().get(0).getProductId());
		assertEquals("Laptop", updated.getLineItems().get(0).getProductName());
		assertEquals(40000, updated.getLineItems().get(0).getPrice());
		
		assertEquals(2, updated.getLineItems().get(1).getItemId());
		assertEquals(11, updated.getLineItems().get(1).getProductId());
		assertEquals("TV", updated.getLineItems().get(1).getProductName());
		assertEquals(25000, updated.getLineItems().get(1).getPrice());
	}
	
	@Test
	public void searchCartTest() throws CartNotFoundException {
		
		
		List<LineItem> items= new ArrayList<LineItem>();
		items.add(new LineItem(1,10,"Laptop", 2, 40000));
		Cart cart= new Cart(101, items);
		
		
	
		
		when(repo.findById(anyInt()))
		.thenReturn(Optional.ofNullable(cart))
		.thenReturn(Optional.ofNullable(cart));
	
		
		Cart found= service.searchCart(101);
	
		assertEquals(1, found.getLineItems().get(0).getItemId());
		assertEquals(10, found.getLineItems().get(0).getProductId());
		assertEquals("Laptop", found.getLineItems().get(0).getProductName());
		assertEquals(40000, found.getLineItems().get(0).getPrice());
		
	}
	
	

}





