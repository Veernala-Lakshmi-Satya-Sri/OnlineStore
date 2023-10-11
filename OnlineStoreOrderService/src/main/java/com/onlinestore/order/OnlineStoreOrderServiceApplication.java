package com.onlinestore.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OnlineStoreOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreOrderServiceApplication.class, args);
	}

}
