package com.onlinestore.product.controller;

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
import com.onlinestore.product.entity.Product;
import com.onlinestore.product.service.ProductInterface;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	MockMvc mockmvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	ProductInterface productInterface;
	
	@Test
	public void addProductTest() throws Exception {
		Product p= new Product(2,"TV", "25Inch BEST TV", 25000);
	
	when(productInterface.addProduct(any())).thenReturn(p);
	RequestBuilder request= MockMvcRequestBuilders.post("/product").
				contentType("application/json").content(mapper.writeValueAsString(p));
	
	MvcResult result=	mockmvc.perform(request).andExpect(status().isCreated()).andReturn();
    
    String  response =	result.getResponse().getContentAsString();
    
    
     Product added = mapper.readValue(response, Product.class);
    
     assertEquals(2, added.getProductId());
     assertEquals("TV", added.getProductName());
     assertEquals("25Inch BEST TV", added.getDescription());
     assertEquals(25000, added.getPrice());
     
     assertThat(response).isEqualToIgnoringWhitespace(mapper.writeValueAsString(p));
		
	}
	
	
	@Test
	public void deleteProductTest() throws Exception {
	
	when(productInterface.deleteProduct(anyInt())).thenReturn("deleted");
	
	RequestBuilder request= MockMvcRequestBuilders.delete("/product/{id}",1);
	
		
	MvcResult result=	mockmvc.perform(request).andExpect(status().isOk()).andReturn();
    
    String  response =	result.getResponse().getContentAsString();
    
    assertEquals("deleted", response);
  
     
		
	}

	
	
	@Test
	public void searchProductTest() throws Exception {
		Product p= new Product(2,"TV", "25Inch BEST TV", 25000);
	
	when(productInterface.searchProduct(anyInt())).thenReturn(p);
	
	//RequestBuilder request= MockMvcRequestBuilders.get("/product/{id}",1);
	
	//MvcResult result=	mockmvc.perform(request).andExpect(status().isCreated()).andReturn();
    
  //  String  response =	result.getResponse().getContentAsString();
    
	String response= mockmvc.perform( MockMvcRequestBuilders.get("/product/{id}",1))
		  .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    
     Product found = mapper.readValue(response, Product.class);
    
     assertEquals(2, found.getProductId());
     assertEquals("TV", found.getProductName());
     assertEquals("25Inch BEST TV", found.getDescription());
     assertEquals(25000, found.getPrice());
     
     assertThat(response).isEqualToIgnoringWhitespace(mapper.writeValueAsString(p));
		
	}
	
	
	
	@Test
	public void updateProductTest() throws Exception {
		Product p= new Product(2,"TV", "25Inch BEST TV", 25000);
		Product p2= new Product(2,"TV2", "25Inch BEST TV2", 30000);

	when(productInterface.updateProduct(anyInt(), any())).thenReturn(p);
	

    
	String response= mockmvc.perform( MockMvcRequestBuilders.put("/product/{id}",1)
			.contentType("application/json")
			.content(mapper.writeValueAsString(p2)))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    
     Product found = mapper.readValue(response, Product.class);
    
     assertEquals(2, found.getProductId());
     assertEquals("TV", found.getProductName());
     assertEquals("25Inch BEST TV", found.getDescription());
     assertEquals(25000, found.getPrice());
     
     assertThat(response).isEqualToIgnoringWhitespace(mapper.writeValueAsString(p));
		
	}
}
