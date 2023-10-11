package com.onlinestore.shipping.Decoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.onlinestore.shipping.Exceptions.CustomException2;
import com.onlinestore.shipping.Exceptions.ExceptionsResponseFormat;
import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;

import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder{
	
	private final ErrorDecoder errorDecoder = new Default();
	private static final Logger Log= LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    	ErrorResponse customError= null;
	
        String body = "5xx client error";

    	try(InputStream bodyIs= response.body().asInputStream()) {
    		if(response.status() != 503) {
    
    			 customError= mapper.readValue(bodyIs,ErrorResponse.class);
    		}
    		
    		
    		else {
    			body = new BufferedReader(response.body().asReader(StandardCharsets.UTF_8))
    	                .lines()
    	                .collect(Collectors.joining("\n"));
    	       
    		}
    	}
    	catch (Exception e) {
          return new Exception(e);
    	}
    	
    	if(response.status()>=400 && response.status()<499) {
    		return new CustomException2(customError.getError());
    	}
    	else if(response.status()>=500) {
    		return new ServiceUnavailableException(body);
    	}
  
       return errorDecoder.decode(methodKey, response);
        
		
    }
}