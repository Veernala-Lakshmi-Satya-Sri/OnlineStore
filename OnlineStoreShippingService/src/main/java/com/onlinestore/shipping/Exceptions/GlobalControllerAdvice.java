package com.onlinestore.shipping.Exceptions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinestore.shipping.Decoder.ErrorResponse;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;




@RestControllerAdvice
public class GlobalControllerAdvice {

	
	ObjectMapper mapper= new ObjectMapper();
	


	
	  @ExceptionHandler(EmptyCartException.class)
	    ExceptionsResponseFormat handleEmptyCartException(EmptyCartException exception, HttpServletRequest req) throws Exception {
	    	System.out.print(exception);
	    	ExceptionsResponseFormat e= new ExceptionsResponseFormat();
	    	e.setUrl(req.getRequestURI());
	    	e.setError(exception.getMessage());
	    	
	    	return e;
	    	
	  }
	  
	  @ExceptionHandler(InSufficientQuantity.class)
	    ExceptionsResponseFormat handleInSufficientQuantity(InSufficientQuantity exception, HttpServletRequest req) throws Exception {
	    	System.out.print(exception);
	    	ExceptionsResponseFormat e= new ExceptionsResponseFormat();
	    	e.setUrl(req.getRequestURI());
	    	e.setError(exception.getMessage());
	    	
	    	return e;
	    	
	  }
	   
	  

//    @ExceptionHandler(FeignException.class)
//    ExceptionsResponseFormat handleFeignStatusException(FeignException exception, HttpServletRequest req) throws Exception {
//    	System.out.println("FeignException");
//       // mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//    	//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    	ExceptionsResponseFormat e= new ExceptionsResponseFormat();
//    	System.out.print(exception);
//    	String msg =exception.contentUTF8();
//    	System.out.println(msg);
//    	 exception.getMessage();
//    	
//    	e.setUrl("/dummy");
//    	e.setError("Load balancer");
//    	return e;
//    	
//        
//    }
//	
    
    @ExceptionHandler(CustomException.class)
    ExceptionsResponseFormat handleOrderNotFoundException(CustomException exception, HttpServletRequest req) throws Exception {
    	System.out.print(exception);
    	ExceptionsResponseFormat e= new ExceptionsResponseFormat();
    	e.setUrl(req.getRequestURI());
    	e.setError(exception.getMessage());
    	
    	return e;
    	
  }
    
    @ExceptionHandler(CustomException2.class)
    public ExceptionsResponseFormat handleCustomException(CustomException2 exception,HttpServletRequest req) throws Exception {
    	ExceptionsResponseFormat e= new ExceptionsResponseFormat();
    	e.setUrl(req.getRequestURI());
    	e.setError(exception.getMessage());	

    	return e;
    	
  }
   
    
    @ExceptionHandler(ServiceUnavailableException.class)
    ExceptionsResponseFormat ServiceUnavailableExceptionException(ServiceUnavailableException exception, HttpServletRequest req) throws Exception {
    	ExceptionsResponseFormat e= new ExceptionsResponseFormat();
    	e.setUrl(req.getRequestURI());
    	e.setError(exception.getMessage());
    	
    	return e;
    	
  }
	
}
