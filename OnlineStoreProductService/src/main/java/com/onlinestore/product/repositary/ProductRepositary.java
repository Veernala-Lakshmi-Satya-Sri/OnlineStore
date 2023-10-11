package com.onlinestore.product.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.product.entity.Product;

public interface ProductRepositary extends JpaRepository<Product, Integer>{

}
