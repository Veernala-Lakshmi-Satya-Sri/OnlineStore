package com.onlinestore.order.service;

import org.springframework.stereotype.Service;

import com.onlinestore.order.Exceptions.OrderNotFoundException;

import com.onlinestore.order.entity.Orders;

@Service
public interface OrderInterface {

	Orders addOrder(Orders order);

	String deleteOrder(int id) throws OrderNotFoundException;

	Orders updateOrder(int id, Orders cart) throws OrderNotFoundException;

	Orders searchOrder(int id) throws OrderNotFoundException;

}
