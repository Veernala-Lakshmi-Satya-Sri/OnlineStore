package com.onlinestore.inventory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinestore.inventory.Service.InventoryInterface;
import com.onlinestore.inventory.model.Inventory;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(InventoryController.class)
class InventoryControllerTest {


	@Autowired
	MockMvc mockmvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	InventoryInterface inventoryInterface;
	
	@Test
	public void addProductTest() throws Exception {
	
	Inventory inv= new Inventory(1,12,25);
	when(inventoryInterface.addInventory(any())).thenReturn(inv);
	RequestBuilder request= MockMvcRequestBuilders.post("/inventory").
				contentType("application/json").content(mapper.writeValueAsString(inv));
	
	MvcResult result=	mockmvc.perform(request).andExpect(status().isCreated()).andReturn();
    
    String  response =	result.getResponse().getContentAsString();
    
    
    Inventory added = mapper.readValue(response, Inventory.class);
    
    assertEquals(12, added.getProductId());
   
    assertEquals(25, added.getQuantity()); 
    assertThat(response).isEqualToIgnoringWhitespace(mapper.writeValueAsString(inv));
		
	}
	
	
	@Test
	public void deleteProductTest() throws Exception {
		Inventory inv= new Inventory(1,12,25);
		when(inventoryInterface.deleteInventory(anyInt())).thenReturn("deleted");
		RequestBuilder request= MockMvcRequestBuilders.delete("/inventory/{pid}",12);
		
		MvcResult result=	mockmvc.perform(request).andExpect(status().isOk()).andReturn();
	    
	    String  response =	result.getResponse().getContentAsString();
	    
	    assertEquals("deleted", response);
	
     
		
	}

	
	
	@Test
	public void searchProductTest() throws Exception {
		Inventory inv= new Inventory(1,12,25);
		when(inventoryInterface.searchInventory(anyInt())).thenReturn(inv);
		RequestBuilder request= MockMvcRequestBuilders.get("/inventory/{pid}",12);
		
		MvcResult result=	mockmvc.perform(request).andExpect(status().isOk()).andReturn();
	    
	    String  response =	result.getResponse().getContentAsString();
	    
	    
	    Inventory found = mapper.readValue(response, Inventory.class);
	    
	    assertEquals(12, found.getProductId());
	   
	    assertEquals(25, found.getQuantity()); 
	    assertThat(response).isEqualToIgnoringWhitespace(mapper.writeValueAsString(inv));
		
	}
	
	
	
	@Test
	public void updateProductTest() throws Exception {
		Inventory inv= new Inventory(1,12,25);
		when(inventoryInterface.updateInventory(anyInt(),any())).thenReturn(inv);
		RequestBuilder request= MockMvcRequestBuilders.put("/inventory/{pid}",12).
					contentType("application/json").content(mapper.writeValueAsString(inv));
		
		MvcResult result=	mockmvc.perform(request).andExpect(status().isOk()).andReturn();
	    
	    String  response =	result.getResponse().getContentAsString();
	    
	    
	    Inventory added = mapper.readValue(response, Inventory.class);
	    
	    assertEquals(12, added.getProductId());
	   
	    assertEquals(25, added.getQuantity()); 
	    assertThat(response).isEqualToIgnoringWhitespace(mapper.writeValueAsString(inv));

		
	}
}



