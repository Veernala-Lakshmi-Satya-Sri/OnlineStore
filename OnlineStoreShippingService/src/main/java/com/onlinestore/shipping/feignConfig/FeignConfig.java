package com.onlinestore.shipping.feignConfig;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.onlinestore.shipping.Decoder.CustomErrorDecoder;
import com.onlinestore.shipping.Exceptions.CustomException2;
import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class FeignConfig {
	
	@Bean
	ErrorDecoder errorDecoder() {
	 return new CustomErrorDecoder();
 }
	
//	@Bean
//	public Retryer retryer() {
//		return new Retryer.Default();
//	}
//	
//	@Bean
//	CircuitBreaker reportingApiCircuitBreaker(CircuitBreakerRegistry registry) {
//	    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
//	        .recordExceptions(ServiceUnavailableException.class)
//	        .ignoreExceptions(CustomException2.class)
//	        .build();
//
//	    return registry.circuitBreaker("external", config);
//	}
	
	
//	
//	CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//			  .failureRateThreshold(50)
//			  .waitDurationInOpenState(Duration.ofMillis(1000))
//			  .slidingWindowSize(2)
//			  .ignoreExceptions(CustomException2.class)
//			  .recordExceptions(ServiceUnavailableException.class)
//			  .build();
//	TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//			  .timeoutDuration(Duration.ofSeconds(4))
//			  .build();
//	
//	@Bean
//	public Customizer<Resilience4JCircuitBreakerFactory> specificCustomConfiguration1() {
//
//	    // the circuitBreakerConfig and timeLimiterConfig objects
//
//	    return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
//	      .timeLimiterConfig(timeLimiterConfig).build(), "external");
//	}
//	


//	@Bean
//	CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//			  .failureRateThreshold(50)
//			  .slowCallRateThreshold(50)
//			  .waitDurationInOpenState(Duration.ofMillis(1000))
//			  .slowCallDurationThreshold(Duration.ofSeconds(2))
//			  .permittedNumberOfCallsInHalfOpenState(3)
//			  .minimumNumberOfCalls(10)
//			  .slidingWindowType(SlidingWindowType.TIME_BASED)
//			  .slidingWindowSize(5)
//			  .recordExceptions(ServiceUnavailableException.class)
//			  .ignoreExceptions(CustomException2.class)
//			  .build();
//	
	
//	CircuitBreakerRegistry circuitBreakerRegistry = 
//			  CircuitBreakerRegistry.of(circuitBreakerConfig);
}
