package com.onlinestore.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.product.Exceptions.ProductNotFoundException;
import com.onlinestore.product.entity.Product;
import com.onlinestore.product.repositary.ProductRepositary;


@Service
public class ProductService implements ProductInterface {
	
	@Autowired
	ProductRepositary productRepo;

	@Override
	public Product addProduct(Product product) {
		
		return productRepo.save(product);
	}

	@Override
	public String deleteProduct(int id) throws ProductNotFoundException {
		
		searchProduct(id);
		productRepo.deleteById(id);
		if(productRepo.findById(id).isEmpty()) {
			return "deleted";
		}
		else {
			return "Not Deleted";
		}
		
				
	}

	@Override
	public Product updateProduct(int id, Product product) throws ProductNotFoundException {
		Product oldProduct= searchProduct(id);
		oldProduct.setProductName(product.getProductName());
		oldProduct.setDescription(product.getDescription());
		oldProduct.setPrice(product.getPrice());
		
		return productRepo.save(oldProduct);
	}

	@Override
	public Product searchProduct(int id) throws ProductNotFoundException {
		if(productRepo.findById(id).isEmpty()) {
			throw new ProductNotFoundException("Product with given ID is not present");
		}
		return productRepo.findById(id).get();
	}

}
