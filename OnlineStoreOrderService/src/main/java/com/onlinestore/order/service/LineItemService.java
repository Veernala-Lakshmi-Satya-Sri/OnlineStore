package com.onlinestore.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.order.Exceptions.LineItemNotFoundException;
import com.onlinestore.order.Exceptions.OrderNotFoundException;
import com.onlinestore.order.entity.LineItem;
import com.onlinestore.order.entity.Orders;
import com.onlinestore.order.repositary.LineItemRepositary;
import com.onlinestore.order.repositary.OrderRepositary;

@Service
public class LineItemService implements LineItemInterface{
	
	@Autowired
	LineItemRepositary lineItemRepo;
	
	@Autowired
	OrderRepositary orderRepo;
	
@Autowired
OrderInterface orderInterface;
	
	@Override
	public LineItem addLineItem(int orderid, LineItem lineItem) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		Orders o=	orderInterface.searchOrder(orderid);
	
		List<LineItem> items= o.getLineItems();
		
		List<LineItem> i= new ArrayList<LineItem>();
		i.add(lineItem);
		o.setLineItems(i);
		
		List<LineItem> added=	orderRepo.save(o).getLineItems();
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
		item.setPrice(lineItem.getPrice());
		item.setProductId(lineItem.getProductId());
		item.setProductName(lineItem.getProductName());
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

}
