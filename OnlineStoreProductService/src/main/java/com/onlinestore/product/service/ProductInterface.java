package com.onlinestore.product.service;

import org.springframework.stereotype.Service;

import com.onlinestore.product.Exceptions.ProductNotFoundException;
import com.onlinestore.product.entity.Product;


@Service
public interface ProductInterface {

	Product addProduct(Product product);

	String deleteProduct(int id) throws ProductNotFoundException;

	Product updateProduct(int id, Product product) throws ProductNotFoundException;

	Product searchProduct(int id) throws ProductNotFoundException;

}
