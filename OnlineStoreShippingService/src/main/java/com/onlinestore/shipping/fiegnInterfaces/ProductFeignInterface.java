package com.onlinestore.shipping.fiegnInterfaces;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlinestore.shipping.Decoder.CustomErrorDecoder;
import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;
import com.onlinestore.shipping.dto.Product;
//import com.onlinestore.shipping.feignConfig.FeignConfig;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;



@CircuitBreaker(name= "external", fallbackMethod = "fallback")
@Retry(name = "myretry")
@FeignClient(name="PRODUCT-SERVICE",configuration = {CustomErrorDecoder.class})
public interface ProductFeignInterface {

	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product);
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id);
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product);
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> searchProduct(@PathVariable int id);

	
	default ResponseEntity<Product> fallback(ServiceUnavailableException e)  {
		throw new ServiceUnavailableException(e.getMessage());
	}
	
	
}


//@Component
//class ProductFallbackFactory implements FallbackFactory<ProductFeignInterface>{
//
//	@Override
//	public ProductFeignInterface create(Throwable cause) {
//		// TODO Auto-generated method stub
//	return new ProductFeignInterface() {
//
//		@Override
//		public ResponseEntity<Product> addProduct(Product product) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public ResponseEntity<String> deleteProduct(int id) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public ResponseEntity<Product> updateProduct(int id, Product product) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public ResponseEntity<Product> searchProduct(int id) {
//			// TODO Auto-generated method stub
//			System.out.println("Fall back factory");
//			//System.out.println(cause.getMessage());
//			if(cause instanceof FeignException) {
//				System.out.println("cause instanceof FeignException");
//				throw (FeignException)cause;
//			}
//			else {
//				throw new CustomException2(cause.getMessage());
//			}
//			
//			
//		}
//		
//		
//	};
//
//	}
//}
