package com.onlinestore.customer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlinestore.customer.Exceptions.AddressNotFoundException;
import com.onlinestore.customer.Exceptions.CustomerNotFoundException;
import com.onlinestore.customer.Exceptions.InValidCredintialsException;
import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.entity.ShippingAddress;
import com.onlinestore.customer.repositary.AddressRepositary;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	
	@Mock
	AddressRepositary addressRepo;
	
	@Mock
	CustomerInterface customerService;
	
	@InjectMocks
	AddressService addressService;
	
	
	@Test
	public void addAddressTest() throws CustomerNotFoundException, InValidCredintialsException {
		
		List<BiilingAddress> a_list= new ArrayList<BiilingAddress>();

		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",12345);
		a_list.add(a1);
		
		List<ShippingAddress> a_list2= new ArrayList<ShippingAddress>();

		ShippingAddress	a2= new ShippingAddress(1,123,"lgb","xyz","Banglore",12345);
		a_list2.add(a2);
		Customer c= new Customer(10,"Satya","satya@gmail.com",a_list,a_list2);
		
		BiilingAddress	a3= new BiilingAddress(1,333,"added lbg","xyz","Banglore2",123456);
		
		when(customerService.searchCustomer(10)).thenReturn(c);
		when(addressRepo.save(a3)).thenReturn(a3);
		
		Customer returned=addressService.addCustomerAddress(10, a3);
		
		assertEquals(2, returned.getBillingAddresses().size());
		
		
		
	}
	
	@Test
	public void searchCustomerAddressTest() throws AddressNotFoundException {
		

		
		BiilingAddress	a2= new BiilingAddress(1,333,"lbg","xyz","Banglore2",123456);
		
		when(addressRepo.findById(1)).thenReturn(Optional.ofNullable(a2)).thenReturn(Optional.ofNullable(a2));
		
		BiilingAddress returned=addressService.searchCustomerAddress(1);
		
		
		assertEquals(333, returned.getDoorNo());
		assertEquals("lbg", returned.getStreetName());
		assertEquals("xyz", returned.getLayout());
		assertEquals("Banglore2", returned.getCity());
		assertEquals(123456, returned.getPin());
	}
	
	
	
	@Test
	public void deleteCustomerAddressTest() throws CustomerNotFoundException, AddressNotFoundException {
		
		
		BiilingAddress	a1= new BiilingAddress(1,123,"lgb","xyz","Banglore",123456);

		when(addressRepo.findById(1)).
		thenReturn(Optional.ofNullable(a1))
		.thenReturn(Optional.ofNullable(a1))
		.thenReturn(Optional.empty());
		
		String msg= addressService.deleteCustomerAddress(1);
		assertEquals("deleted", msg);
		
		verify(addressRepo, times(1)).deleteById(1);
		
	}
	
	
	
	@Test
	public void updateCustomerAddressTest() throws AddressNotFoundException, InValidCredintialsException  {
		
		BiilingAddress	oldAddress= new BiilingAddress(1,222,"old lbg","old xyz","Banglore",123456);
		
		BiilingAddress	newAddress= new BiilingAddress(1,333,"new lbg","new xyz","Banglore2",123456);
		
		
		
		when(addressRepo.findById(1)).thenReturn(Optional.ofNullable(oldAddress)).thenReturn(Optional.ofNullable(oldAddress));
		when(addressRepo.save(oldAddress)).thenReturn(oldAddress);
		BiilingAddress returned=addressService.updateCustomerAddress(1, newAddress);
		
		
		assertEquals(333, returned.getDoorNo());
		assertEquals("new lbg", returned.getStreetName());
		assertEquals(oldAddress.getLayout(), returned.getLayout());
		assertEquals(oldAddress.getCity(), returned.getCity());
		assertEquals(123456, returned.getPin());
	}
	
}








