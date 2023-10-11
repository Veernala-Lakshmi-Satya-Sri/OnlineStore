package com.onlinestore.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.cart.Exceptions.CartNotFoundException;
import com.onlinestore.cart.entity.Cart;
import com.onlinestore.cart.entity.LineItem;

import com.onlinestore.cart.repositary.CartRepositary;

@Service
public class CartService implements CartInterface{

	@Autowired
	private CartRepositary cartRepo;
	
	
	@Override
	public Cart addCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartRepo.save(cart);
	}

	@Override
	public String deleteCart(int id) throws CartNotFoundException {
		// TODO Auto-generated method stub
		Cart cart=searchCart(id);
	
		
		cartRepo.deleteById(id);
		if(cartRepo.findById(id).isEmpty()) {
			return "deleted";
					
		}
		else {
			return "not deleted";
		}
		
	}
	

	@Override
	public Cart updateCart(int id, Cart cart) throws CartNotFoundException {
		// TODO Auto-generated method stub
		Cart c= searchCart(id);
		List<LineItem> items= cart.getLineItems();
		if(c.getLineItems().size()==0) {
			
			c.setLineItems(cart.getLineItems());
		}
		
		else {
			for(LineItem i:items) {
				c.getLineItems().add(i);
			}
		}
		
		//c.setLineItems(cart.getLineItems());
		return cartRepo.save(c);
		 
	}

	@Override
	public Cart searchCart(int id) throws CartNotFoundException {
		// TODO Auto-generated method stub
		if(cartRepo.findById(id).isEmpty()) {
			throw new CartNotFoundException("Cart with given ID is not present");
		}
		return cartRepo.findById(id).get();
	}

	@Override
	public Cart emptyCart(int id) throws CartNotFoundException {
		// TODO Auto-generated method stub
	Cart c=	searchCart(id);
	c.setLineItems(null);
		return cartRepo.save(c);
	}
	
	

}
