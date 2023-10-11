//package com.onlinestore.order.entity;
//
//
//import java.util.List;
//
//import org.springframework.data.annotation.ReadOnlyProperty;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonProperty.Access;
//import com.onlinestore.order.entity.LineItem;
//import com.onlinestore.order.entity.Orders;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//
//@Entity
//public class Cart {
//@ReadOnlyProperty
//	@Id 
//	//@JsonProperty(access = Access.READ_ONLY)
//	@SequenceGenerator(initialValue = 101, sequenceName = "ocart", name = "ocart_seq")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ocart_seq")
//	private int cartid;
//	
//	
//	
//	//@OneToOne(mappedBy = "cart")
//	//private Orders order;
//	
//	
//
//
//
//	public List<LineItem> getLineItems() {
//		return lineItems;
//	}
//
//
//
//	public int getCartid() {
//		return cartid;
//	}
//
//
//
//	public void setCartid(int cartid) {
//		this.cartid = cartid;
//	}
//
//
//
//	public void setLineItems(List<LineItem> lineItems) {
//		this.lineItems = lineItems;
//	}
//
//
//
//	public Cart() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//
//
//
//
//	public Cart(int id, List<LineItem> lineItems) {
//		super();
//		this.cartid = id;
//		this.lineItems = lineItems;
//	}
//
//
//	
//	@OneToMany(targetEntity = LineItem.class, cascade = CascadeType.ALL)
//	@JoinColumn(name="cart_id_fk", referencedColumnName = "cartid")
//	List<LineItem> lineItems;
//}
