package com.onlinestore.customer.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;



@RestControllerAdvice
public class CustomerControllerAdvice {

	String message;
	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
//	@ResponseBody 
	ExceptionsResponseFormat handleCustomerNotFoundException(CustomerNotFoundException exception,HttpServletRequest req)
	{
		
		ExceptionsResponseFormat response=new ExceptionsResponseFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
				
		
		return response;
	}
	
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	ExceptionsResponseFormat handleConstraintViolationException(ConstraintViolationException exception,HttpServletRequest req)
	{	
		//List<String> s=new ArrayList<String>();
		
		exception.getConstraintViolations().forEach(v -> message=v.getMessage());
		ExceptionsResponseFormat response=new ExceptionsResponseFormat();
		
		response.setError(message);
		//response.setError(s.toString());
		response.setUrl(req.getRequestURI());
				
		return response;
	}
	
	
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	ExceptionsResponseFormat handleEmailAlreadyExistsException(EmailAlreadyExistsException exception,HttpServletRequest req)
	{
		
		ExceptionsResponseFormat response=new ExceptionsResponseFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
				
		
		return response;
	}
	
	
	@ExceptionHandler(AddressNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	ExceptionsResponseFormat handleAddressNotFoundException(AddressNotFoundException exception,HttpServletRequest req)
	{
		
		ExceptionsResponseFormat response=new ExceptionsResponseFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
				
		
		return response;
	}
	
	@ExceptionHandler(InValidCredintialsException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	ExceptionsResponseFormat handleInValidCredintialsException(InValidCredintialsException exception,HttpServletRequest req)
	{
		
		ExceptionsResponseFormat response=new ExceptionsResponseFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
				
		
		return response;
	}


	
	
}
