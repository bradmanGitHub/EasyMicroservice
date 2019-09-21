package com.test.microservice.mainweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.test.microservice.mainweb")
public class MainwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainwebApplication.class, args);
	}
}
