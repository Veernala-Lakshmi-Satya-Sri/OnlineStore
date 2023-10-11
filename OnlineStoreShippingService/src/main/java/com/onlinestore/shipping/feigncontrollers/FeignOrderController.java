package com.onlinestore.shipping.feigncontrollers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinestore.shipping.Exceptions.EmptyCartException;
import com.onlinestore.shipping.Exceptions.InSufficientQuantity;
import com.onlinestore.shipping.Exceptions.CustomException;
import com.onlinestore.shipping.dto.Customer;
import com.onlinestore.shipping.dto.Inventory;
import com.onlinestore.shipping.entity.Cart;
import com.onlinestore.shipping.entity.LineItem;
import com.onlinestore.shipping.entity.Orders;
import com.onlinestore.shipping.feignRepositaries.Customer_Cart_Repo;
import com.onlinestore.shipping.feignRepositaries.Customer_Order_Repo;
import com.onlinestore.shipping.fiegnInterfaces.CartFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.CartLineItemFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.CustomerFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.InventoryFeignInterface;
import com.onlinestore.shipping.fiegnInterfaces.OrderFeignInterface;
import com.onlinestore.shipping.tables.Customer_Cart;
import com.onlinestore.shipping.tables.Customer_Order;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import rx.internal.schedulers.NewThreadWorker;


@RestController
@RequestMapping("/shippingservice")
@Tag(name = "5. Order APIs")
public class FeignOrderController {

	@Autowired
	OrderFeignInterface orderFeign;
	
	@Autowired
	CartFeignInterface cartFeign;
	
//	@Autowired
//	CartLineItemFeignInterface itemInterface;
	
	
	@Autowired
	InventoryFeignInterface inveFeign;
	
	@Autowired
	CustomerFeignInterface customerFeign;
	
	@Autowired
	Customer_Cart_Repo	customer_cartRepositary;
	
	@Autowired
	Customer_Order_Repo customer_OrderRepositary;
	
	
	List<LineItem> map(List<LineItem> i) {
		Collection<LineItem> cartItems= new ArrayList<LineItem>();
		for(LineItem l: i) {
				cartItems.add(l);
		}
		List<LineItem> items= cartItems.stream().map(o-> 
		new LineItem( o.getProductId(), o.getProductName(),o.getQuantity(),o.getPrice()))
				.collect(Collectors.toList());
		return items;
	}
	
	
	@PostMapping("/order/{customerid}")
	public ResponseEntity<Orders> addOrder(@PathVariable int customerid) throws EmptyCartException, Exception
	
	{
		customerFeign.searchCustomer(customerid);
	
		Customer_Cart cc=	customer_cartRepositary.findByCustomerId(customerid);
		
		ResponseEntity<Cart> c= cartFeign.searchCart(cc.getCartId());
		
		List<LineItem> cartItems= c.getBody().getLineItems();
	
		if(c.getBody().getLineItems().size()==0) {
			throw new EmptyCartException("Cart is Empty, Please add items to Place order");
		}
		
	
		Orders o= new Orders();

		o.setLineItems(map(cartItems));

		
		for(LineItem i: o.getLineItems()) {
		int q=inveFeign.searchInventory(i.getProductId()).getBody().getQuantity();
		if(q < i.getQuantity() && q!=0) {
			throw new InSufficientQuantity("Only "+ q+ " left for product with Id "+ i.getProductId());
			
		} 
		else {
				if(q==0)
				throw new InSufficientQuantity("Out Of Stock, Product Id:  "+ i.getProductId());
			}
		}
		
		ResponseEntity<Orders> placedOrder = orderFeign.addOrder(o);
		System.out.println(placedOrder.getStatusCode());		
		if(placedOrder.getStatusCodeValue()==201) {

			List<LineItem> items = placedOrder.getBody().getLineItems();
			 for(LineItem i: items) {
				
				 int pid =i.getProductId();
				 int qty= inveFeign.searchInventory(pid).getBody().getQuantity();
				 int remain= qty- i.getQuantity();
				 Inventory inv= new Inventory();
				 inv.setQuantity(remain);
				 inveFeign.updateInventory(pid, inv);
			 }
		}
		else {
			throw new CustomException("Cloudn't able to place order");
		}
	
		cartFeign.emptyCart(cc.getCartId());
		
		
		Customer_Order co= new Customer_Order();
		co.setCustomerId(customerid);
		co.setOrderId(placedOrder.getBody().getOrderid());
		
		customer_OrderRepositary.save(co);
		
		
	
		return placedOrder;
	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable int id)
	{
		Customer_Order co=	customer_OrderRepositary.findByOrderId(id);
		
		customer_OrderRepositary.deleteById(co.getId());
		return orderFeign.deleteOrder(id);
	}
	
	
	@PutMapping("/order/{id}")
	public ResponseEntity<Orders> updateOrder(@PathVariable int id,@Valid @RequestBody Orders order){
		return orderFeign.updateOrder(id, order);
	}
	
	@GetMapping("/order/{customerid}")
	public ResponseEntity<Orders> searchOrder(@PathVariable int customerid) throws CustomException {
			
		ResponseEntity<Customer> customer=customerFeign.searchCustomer(customerid);
		
		int orderId=0;	
	
	
		if(customer_OrderRepositary.findByCustomerId(customerid)!= null) {
			
			 orderId= customer_OrderRepositary.findByCustomerId(customerid).getOrderId();
			
		}

		else {
			throw new CustomException("Customer With Id : "+ customerid+ " have no orders");
		}
		
		return orderFeign.searchOrder(orderId);
	}

}
