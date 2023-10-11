package com.onlinestore.cart.service;


import org.springframework.stereotype.Service;

import com.onlinestore.cart.Exceptions.CartNotFoundException;
import com.onlinestore.cart.Exceptions.LineItemNotFoundException;
import com.onlinestore.cart.entity.LineItem;

@Service
public interface LineItemInterface {

	LineItem addLineItem(int cartid,LineItem lineItem) throws CartNotFoundException;

	String deleteLineItem(int id) throws LineItemNotFoundException;

	LineItem updateLineItem(int id, LineItem lineItem) throws LineItemNotFoundException;

	LineItem searchLineItem(int id) throws LineItemNotFoundException;

	String deleteAllLineItems(int cartid) throws CartNotFoundException;

}
