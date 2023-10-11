package com.onlinestore.cart.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.cart.entity.Cart;

public interface CartRepositary extends JpaRepository<Cart, Integer>{

}
