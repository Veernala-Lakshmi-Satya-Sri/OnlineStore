package com.onlinestore.order.Exceptions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;




@RestControllerAdvice
public class GlobalControllerAdvice {

    
    @ExceptionHandler(OrderNotFoundException.class)
    ExceptionsResponseFormat handleOrderNotFoundException(OrderNotFoundException exception, HttpServletRequest req) throws Exception {
    	System.out.print(exception);
    	ExceptionsResponseFormat e= new ExceptionsResponseFormat();
    	e.setUrl(req.getRequestURI());
    	e.setError(exception.getMessage());
    	
    	return e;
    	
  }
	
	   
    @ExceptionHandler(LineItemNotFoundException.class)
    ExceptionsResponseFormat handleLineItemNotFoundException(LineItemNotFoundException exception, HttpServletRequest req) throws Exception {
    	System.out.print(exception);
    	ExceptionsResponseFormat e= new ExceptionsResponseFormat();
    	e.setUrl(req.getRequestURI());
    	e.setError(exception.getMessage());
    	
    	return e;
    	
  }
    
	
}
