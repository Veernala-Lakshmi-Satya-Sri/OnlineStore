package com.onlinestore.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class OnlineStoreConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreConfigServerApplication.class, args);
	}

}
