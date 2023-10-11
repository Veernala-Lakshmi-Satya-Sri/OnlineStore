package com.onlinestore.order.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.order.entity.LineItem;


public interface LineItemRepositary extends JpaRepository<LineItem, Integer>{

}
