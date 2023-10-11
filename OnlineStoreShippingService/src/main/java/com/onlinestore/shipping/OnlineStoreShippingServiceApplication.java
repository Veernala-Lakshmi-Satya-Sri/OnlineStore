package com.onlinestore.shipping;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.onlinestore.shipping.Exceptions.ServiceUnavailableException;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OnlineStoreShippingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreShippingServiceApplication.class, args);
	}
//	@Bean
//	public Customizer<Resilience4JCircuitBreakerFactory> defaulCustomizer() {
//		return factory-> factory.configureDefault(
//				id -> new Resilience4JConfigBuilder(id)
//				.circuitBreakerConfig(
//						CircuitBreakerConfig.ofDefaults()
//						).build()
//				);
//	}
	
//	@Bean
//	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//	    return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//	            .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
//	            .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//	            .build());
//	}

	
}
