package com.onlinestore.shipping.feignRepositaries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.shipping.tables.Customer_Order;

public interface Customer_Order_Repo extends JpaRepository<Customer_Order, Integer>{

	Customer_Order findByCustomerId(int customerid);

	Customer_Order findByOrderId(int id);

}
