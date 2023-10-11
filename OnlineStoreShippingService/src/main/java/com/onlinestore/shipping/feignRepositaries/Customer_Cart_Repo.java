package com.onlinestore.shipping.feignRepositaries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.shipping.tables.Customer_Cart;

public interface Customer_Cart_Repo extends JpaRepository<Customer_Cart, Integer>{

	Customer_Cart findByCustomerId(int customerid);

	Customer_Cart findByCartId(int cartid);

	

}
