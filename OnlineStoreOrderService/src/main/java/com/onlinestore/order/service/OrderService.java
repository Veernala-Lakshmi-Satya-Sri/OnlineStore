package com.onlinestore.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.order.Exceptions.OrderNotFoundException;

import com.onlinestore.order.entity.Orders;
import com.onlinestore.order.repositary.OrderRepositary;

@Service
public class OrderService implements OrderInterface{

	@Autowired
	private OrderRepositary orderRepo;
	
	
	@Override
	public Orders addOrder(Orders order) {
		// TODO Auto-generated method stub
		
		return orderRepo.save(order);
	}

	@Override
	public String deleteOrder(int id) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		searchOrder(id);
		orderRepo.deleteById(id);
		if(orderRepo.findById(id).isEmpty()) {
			return "deleted";
					
		}
		return "not deleted";
	}

	@Override
	public Orders updateOrder(int id, Orders order) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		Orders o= searchOrder(id);
		//c.setCart(null);
		o.setLineItems(order.getLineItems());
		return orderRepo.save(o);
		 
	}

	@Override
	public Orders searchOrder(int id) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		if(orderRepo.findById(id).isEmpty()) {
			throw new OrderNotFoundException("Order with given ID is not present");
		}
		
		
		return orderRepo.findById(id).get();
	}

}
