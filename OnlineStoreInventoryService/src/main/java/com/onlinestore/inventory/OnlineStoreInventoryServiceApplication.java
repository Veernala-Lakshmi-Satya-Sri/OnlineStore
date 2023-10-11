package com.onlinestore.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OnlineStoreInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreInventoryServiceApplication.class, args);
	}

}
