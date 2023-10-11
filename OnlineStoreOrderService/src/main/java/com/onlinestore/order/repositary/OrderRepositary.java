package com.onlinestore.order.repositary;

import org.springframework.data.jpa.repository.JpaRepository;


import com.onlinestore.order.entity.Orders;


public interface OrderRepositary extends JpaRepository<Orders, Integer>{


}
