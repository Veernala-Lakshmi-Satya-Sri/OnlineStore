package com.onlinestore.product.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;



@RestControllerAdvice
public class ProductControllerAdvice {

	String message;
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
//	@ResponseBody 
	ExceptionsResponseFormat handleProductNotFoundException(ProductNotFoundException exception,HttpServletRequest req)
	{
		
		ExceptionsResponseFormat response=new ExceptionsResponseFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
				
		
		return response;
	}
	
	
	

	

	
	
}
