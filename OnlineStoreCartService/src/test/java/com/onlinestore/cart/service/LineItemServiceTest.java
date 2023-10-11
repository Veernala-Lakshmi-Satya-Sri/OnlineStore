package com.onlinestore.cart.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlinestore.cart.repositary.LineItemRepositary;

@ExtendWith(MockitoExtension.class)
class LineItemServiceTest {

	@InjectMocks
	LineItemService service;
	
	@Mock
	LineItemRepositary repo;
	
	
	
	@Test
	public void addLineItem() {
		
		
	}

}
