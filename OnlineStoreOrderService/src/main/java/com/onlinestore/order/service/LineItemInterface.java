package com.onlinestore.order.service;


import org.springframework.stereotype.Service;

import com.onlinestore.order.Exceptions.LineItemNotFoundException;
import com.onlinestore.order.Exceptions.OrderNotFoundException;
import com.onlinestore.order.entity.LineItem;

@Service
public interface LineItemInterface {

	LineItem addLineItem(int orderid, LineItem lineItem) throws OrderNotFoundException;

	String deleteLineItem(int id) throws LineItemNotFoundException;

	LineItem updateLineItem(int id, LineItem lineItem) throws LineItemNotFoundException;

	LineItem searchLineItem(int id) throws LineItemNotFoundException;

}
