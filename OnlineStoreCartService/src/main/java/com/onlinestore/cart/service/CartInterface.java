package com.onlinestore.cart.service;

import org.springframework.stereotype.Service;

import com.onlinestore.cart.Exceptions.CartNotFoundException;
import com.onlinestore.cart.entity.Cart;

@Service
public interface CartInterface {

	Cart addCart(Cart cart);

	String deleteCart(int id) throws CartNotFoundException;

	Cart updateCart(int id, Cart cart) throws CartNotFoundException;

	Cart searchCart(int id) throws CartNotFoundException;

	Cart emptyCart(int id) throws CartNotFoundException;

}
