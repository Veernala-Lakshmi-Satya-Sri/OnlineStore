package com.onlinestore.customer.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.customer.entity.BiilingAddress;
import com.onlinestore.customer.entity.ShippingAddress;

public interface ShippingAddressRepositary extends JpaRepository<ShippingAddress, Integer>{

}
