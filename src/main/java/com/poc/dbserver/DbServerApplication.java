package com.poc.dbserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class DbServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServerApplication.class, args);
	}

}
