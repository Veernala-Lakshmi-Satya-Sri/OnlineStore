package com.onlinestore.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.cart.Exceptions.CartNotFoundException;
import com.onlinestore.cart.Exceptions.LineItemNotFoundException;
import com.onlinestore.cart.entity.Cart;
import com.onlinestore.cart.entity.LineItem;
import com.onlinestore.cart.repositary.CartRepositary;
import com.onlinestore.cart.repositary.LineItemRepositary;

@Service
public class LineItemService implements LineItemInterface{
	
	@Autowired
	LineItemRepositary lineItemRepo;
	
	@Autowired
	CartInterface cartInterface;
	
	@Autowired
	CartRepositary cartRepo;

	@Override
	public LineItem addLineItem(int cartid, LineItem lineItem) throws CartNotFoundException {
		// TODO Auto-generated method stub
		
		Cart c= cartInterface.searchCart(cartid);
		List<LineItem> items= c.getLineItems();
		
		List<LineItem> i= new ArrayList<LineItem>();
		i.add(lineItem);
		c.setLineItems(i);
		
		List<LineItem> added=	cartRepo.save(c).getLineItems();
		return added.get(0);
		
	
	}

	@Override
	public String deleteLineItem(int id) throws LineItemNotFoundException {
		// TODO Auto-generated method stub
		searchLineItem(id);
		lineItemRepo.deleteById(id);
		if(lineItemRepo.findById(id).isEmpty()) {
			return "deleted";
		}
		return "not deleted" ;
	}

	@Override
	public LineItem updateLineItem(int id, LineItem lineItem) throws LineItemNotFoundException {
		// TODO Auto-generated method stub
		
		LineItem item= searchLineItem(id);
//		item.setPrice(lineItem.getPrice());
//		item.setProductId(lineItem.getProductId());
//		item.setProductName(lineItem.getProductName());
		item.setQuantity(lineItem.getQuantity());
		
		return lineItemRepo.save(item);
	}

	@Override
	public LineItem searchLineItem(int id) throws LineItemNotFoundException {
		// TODO Auto-generated method stub
		if(lineItemRepo.findById(id).isEmpty()) {
			throw new LineItemNotFoundException("LineItem with given ID is not present");
		}
		return lineItemRepo.findById(id).get();
	}

	@Override
	public String deleteAllLineItems(int cartid) throws CartNotFoundException {
		cartInterface.searchCart(cartid);
		// TODO Auto-generated method stub
		 lineItemRepo.deleteAll();
		if( lineItemRepo.count()==0) {
			return "Empty cart";
		}
		else return "Not deleted";
	}

}
