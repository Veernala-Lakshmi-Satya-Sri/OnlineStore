package com.onlinestore.customer.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.customer.entity.BiilingAddress;

public interface AddressRepositary extends JpaRepository<BiilingAddress, Integer>{

}
