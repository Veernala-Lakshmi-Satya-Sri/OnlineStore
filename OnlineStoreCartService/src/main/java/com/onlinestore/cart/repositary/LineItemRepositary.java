package com.onlinestore.cart.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.cart.entity.LineItem;

public interface LineItemRepositary extends JpaRepository<LineItem, Integer>{

}
