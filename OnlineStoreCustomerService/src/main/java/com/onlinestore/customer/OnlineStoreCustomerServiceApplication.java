package com.onlinestore.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OnlineStoreCustomerServiceApplication {


	public static void main(String[] args) {
		
		SpringApplication.run(OnlineStoreCustomerServiceApplication.class, args);
	}

}
