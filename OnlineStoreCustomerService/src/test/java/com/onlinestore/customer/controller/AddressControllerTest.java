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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.entity.ShippingAddress;
import com.onlinestore.customer.service.AddressInterface;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AddressController.class)
class AddressControllerTest {


	@Autowired
	MockMvc mockmvc;
	
	
	@MockBean
	AddressInterface addressInterface;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	@Test
	void addNewCustomerAddressTest() throws Exception {
		List<BiilingAddress> a_list= new ArrayList<BiilingAddress>();
		List<ShippingAddress> a_list2= new ArrayList<ShippingAddress>();
		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",123456);
		ShippingAddress	a2= new ShippingAddress(1,1232,"lgb2","xyz2","Chennai",99999);

		a_list.add(a1);
		a_list2.add(a2);
		
		Customer c= new Customer(10,"Satya","satya@gmail.com",a_list,a_list2);
	
		when(addressInterface.addCustomerAddress(anyInt(), any())).thenReturn(c);
		
		RequestBuilder request=MockMvcRequestBuilders.post("/address/billing/{cid}",1)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(a1));
		
		MvcResult response =mockmvc.perform(request).andExpect(status().isOk()).andReturn();
	
		
		String result=response.getResponse().getContentAsString();
	
		Customer added= objectMapper.readValue(result,Customer.class);
		
		assertThat(result).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(c));
		
		assertThat(Objects.equals(a_list, added.getBillingAddresses()));

		assertEquals("Satya", added.getCustomerName());
		assertEquals("satya@gmail.com", added.getCustomerEmail());
		
		assertEquals(1 ,added.getBillingAddresses().size());
		
		
	}
	
	
	@Test
	void deleteCustomerAddressTest() throws Exception {
		
		when(addressInterface.deleteCustomerAddress(1)).thenReturn("deleted");
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/address/billing/{aid}",1);
								
		MvcResult response =mockmvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String result=response.getResponse().getContentAsString();
		
		assertEquals("deleted", result);
								
		
		
	}
	
	
	@Test
	void updateCustomerAddressTest() throws Exception {
		
		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",123456);

		BiilingAddress	a2= new BiilingAddress(1,123,"new lgb","new xyz","new Banglore",123456);

		
		when(addressInterface.updateCustomerAddress(anyInt(),any())).thenReturn(a2);
		RequestBuilder request= MockMvcRequestBuilders.put("/address/billing/{aid}",1)
								.contentType("application/json")
								.content(objectMapper.writeValueAsBytes(a1));
		
		MvcResult response=mockmvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String result = response.getResponse().getContentAsString();
		
		BiilingAddress updated= objectMapper.readValue(result,BiilingAddress.class);
		
		assertThat(result).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(a2));
		
		assertEquals("new lgb", updated.getStreetName());
		assertEquals("new xyz", updated.getLayout());
		assertEquals("new Banglore", updated.getCity());
		
	}
	
	@Test
	void searchCustomerAddressTest() throws Exception {
		BiilingAddress	a1= new BiilingAddress(1,123,"lbg","xyz","Banglore",123456);

		when(addressInterface.searchCustomerAddress(anyInt())).thenReturn(a1);
		RequestBuilder request= MockMvcRequestBuilders.get("/address/billing/{aid}",1);
		
		MvcResult response= mockmvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String result= response.getResponse().getContentAsString();
		
		BiilingAddress res= objectMapper.readValue(result,BiilingAddress.class);

		assertThat(result).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(a1));

		assertEquals("lbg", res.getStreetName());
		assertEquals("xyz", res.getLayout());
		assertEquals("Banglore", res.getCity());
	}


}
