package com.onlinestore.customer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.entity.ShippingAddress;
import com.onlinestore.customer.service.AddressInterface;
import com.onlinestore.customer.service.CustomerInterface;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	MockMvc mockmvc;
	
	
	@MockBean
	CustomerInterface customerInterface;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	@Test
	void addNewCustomerTest() throws Exception {
		List<BiilingAddress> a_list= new ArrayList<BiilingAddress>();
		List<ShippingAddress> a_list2= new ArrayList<ShippingAddress>();
		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",123456);
		ShippingAddress	a2= new ShippingAddress(1,1232,"lgb2","xyz2","Chennai",99999);

		a_list.add(a1);
		a_list2.add(a2);
		
		Customer c= new Customer(10,"Satya","satya@gmail.com",a_list,a_list2);
	
		when(customerInterface.addCustomer(any())).thenReturn(c);
		
		RequestBuilder request=MockMvcRequestBuilders.post("/customer")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(c));
		
		MvcResult response =mockmvc.perform(request).andExpect(status().isCreated()).andReturn();
	
		
		String result=response.getResponse().getContentAsString();
	
		Customer addedCustomer= objectMapper.readValue(result,Customer.class);
		
		assertThat(result).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(c));
		
		assertThat(Objects.equals(a_list, addedCustomer.getBillingAddresses()));
		assertThat(Objects.equals(a_list2, addedCustomer.getShippingAddresses()));
		assertEquals("Satya", addedCustomer.getCustomerName());
		assertEquals("satya@gmail.com", addedCustomer.getCustomerEmail());
		
		
	}
	
	
	@Test
	void deleteCustomerTest() throws Exception {
		when(customerInterface.deleteCustomer(1)).thenReturn("deleted");
		RequestBuilder request = MockMvcRequestBuilders.delete("/customer/{id}",1);
								
		MvcResult response =mockmvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String result=response.getResponse().getContentAsString();
		assertEquals("deleted", result);
								
		
		
	}
	
	
	@Test
	void updateCustomerTest() throws Exception {
		
	
		Customer c= new Customer(10,"Satya","satya@gmail.com");
		
		Customer c1= new Customer(10,"new Satya","satya123@gmail.com");
		
		when(customerInterface.updateCustomer(anyInt(),any())).thenReturn(c1);
		
		RequestBuilder request=MockMvcRequestBuilders.put("/customer/{id}", 10)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(c));
		
		MvcResult response =mockmvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String result=response.getResponse().getContentAsString();
		
		Customer editedCustomer= objectMapper.readValue(result,Customer.class);
		
		
		assertEquals("new Satya", editedCustomer.getCustomerName());
		assertEquals("satya123@gmail.com", editedCustomer.getCustomerEmail());
		assertThat(result).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(c1));
	}
	
	@Test
	void searchCustomerTest() throws Exception {
		
		List<BiilingAddress> a_list= new ArrayList<BiilingAddress>();
		List<ShippingAddress> a_list2= new ArrayList<ShippingAddress>();
		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",123456);
		ShippingAddress	a2= new ShippingAddress(1,1232,"lgb2","xyz2","Chennai",99999);

		a_list.add(a1);
		a_list2.add(a2);
		Customer c= new Customer(10,"Satya","satya@gmail.com",a_list,a_list2);

		when(customerInterface.searchCustomer(anyInt())).thenReturn(c);
		
		RequestBuilder request=MockMvcRequestBuilders.get("/customer/{id}", 10);
		
		MvcResult response =mockmvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String result=response.getResponse().getContentAsString();
		
		Customer returned= objectMapper.readValue(result,Customer.class);
		
		
		assertEquals("Satya", returned.getCustomerName());
		assertEquals("satya@gmail.com", returned.getCustomerEmail());
		assertThat(result).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(c));
	}

	
}
