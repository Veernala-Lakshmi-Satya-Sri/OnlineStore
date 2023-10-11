package com.onlinestore.cart.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;



@RestControllerAdvice
public class CartControllerAdvice {

	
	
	@ExceptionHandler(CartNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
//	@ResponseBody 
	ExceptionsResponseFormat handleCartNotFoundException(CartNotFoundException exception,HttpServletRequest req)
	{
		
		ExceptionsResponseFormat response=new ExceptionsResponseFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
				
		
		return response;
	}
	
	
	
	
	@ExceptionHandler(LineItemNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
//	@ResponseBody 
	ExceptionsResponseFormat handleLineItemNotFoundException(LineItemNotFoundException exception,HttpServletRequest req)
	{
		
		ExceptionsResponseFormat response=new ExceptionsResponseFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
				
		
		return response;
	}
	

	
	
}
