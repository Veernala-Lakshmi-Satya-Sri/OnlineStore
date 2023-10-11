package com.onlinestore.customer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.customer.Exceptions.AddressNotFoundException;
import com.onlinestore.customer.Exceptions.CustomerNotFoundException;
import com.onlinestore.customer.Exceptions.InValidCredintialsException;
import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.Customer;
import com.onlinestore.customer.entity.ShippingAddress;
import com.onlinestore.customer.repositary.AddressRepositary;
import com.onlinestore.customer.repositary.ShippingAddressRepositary;

import jakarta.validation.Valid;


@Service
public class AddressService implements AddressInterface{
	
	@Autowired
	CustomerInterface customerInterface;
	
	@Autowired
	AddressRepositary billingaddressRepo;
	
	@Autowired
	ShippingAddressRepositary shippingaddressRepo;
	
	@Override
	public Customer addCustomerAddress(int id,@Valid  BiilingAddress address) throws CustomerNotFoundException, InValidCredintialsException {
		// TODO Auto-generated method stub
		Customer c= customerInterface.searchCustomer(id);

		c.getBillingAddresses().add(address);
		
		billingaddressRepo.save(address);
		
		return c;
	}


	@Override
	public BiilingAddress searchCustomerAddress(int aid) throws AddressNotFoundException {
		// TODO Auto-generated method stub
	
		if(billingaddressRepo.findById(aid).isEmpty()) {
			throw new AddressNotFoundException("No Address Present with given ID");
		}
		return billingaddressRepo.findById(aid).get();
	}

	@Override
	public String deleteCustomerAddress(int aid) throws AddressNotFoundException {
		// TODO Auto-generated method stub
		searchCustomerAddress(aid);
		billingaddressRepo.deleteById(aid);
		if(billingaddressRepo.findById(aid).isEmpty()) {
			return "deleted";
		}
		return "Not Deleted";
	}


	@Override
	public BiilingAddress updateCustomerAddress(int aid,@Valid  BiilingAddress address) throws AddressNotFoundException, InValidCredintialsException {
		// TODO Auto-generated method stub
		BiilingAddress adrs=searchCustomerAddress(aid);
		
		
		adrs.setCity(address.getCity());
		adrs.setDoorNo(address.getDoorNo());
		adrs.setLayout(address.getLayout());
		adrs.setPin(address.getPin());
		adrs.setStreetName(address.getStreetName());
		
		return billingaddressRepo.save(adrs);
	}
	
	
	@Override
	public Customer addCustomerShippingAddress(int id,@Valid  ShippingAddress address) throws CustomerNotFoundException, InValidCredintialsException {
			// TODO Auto-generated method stub
			Customer c= customerInterface.searchCustomer(id);

			c.getShippingAddresses().add(address);
			
			shippingaddressRepo.save(address);
			
			return c;
		}


	@Override
	public ShippingAddress searchCustomerShippingAddress(int aid) throws AddressNotFoundException {
		
			if(shippingaddressRepo.findById(aid).isEmpty()) {
				throw new AddressNotFoundException("No Address Present with given ID");
			}
			return shippingaddressRepo.findById(aid).get();
		}

	@Override
	public String deleteCustomerShippingAddress(int aid) throws AddressNotFoundException {
			searchCustomerAddress(aid);
			shippingaddressRepo.deleteById(aid);
			if(shippingaddressRepo.findById(aid).isEmpty()) {
				return "deleted";
			}
			return "Not Deleted";
		}


	@Override
	public ShippingAddress updateCustomerShippingAddress(int aid,@Valid  ShippingAddress address) throws AddressNotFoundException, InValidCredintialsException {
			
			ShippingAddress adrs=searchCustomerShippingAddress(aid);
			
			
			adrs.setCity(address.getCity());
			adrs.setDoorNo(address.getDoorNo());
			adrs.setLayout(address.getLayout());
			adrs.setPin(address.getPin());
			adrs.setStreetName(address.getStreetName());
			
			return shippingaddressRepo.save(adrs);
		}


}



