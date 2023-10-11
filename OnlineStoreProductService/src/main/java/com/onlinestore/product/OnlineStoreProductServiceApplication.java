package com.onlinestore.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import jakarta.persistence.Entity;

@SpringBootApplication
@EnableDiscoveryClient
public class OnlineStoreProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreProductServiceApplication.class, args);
	}

}
