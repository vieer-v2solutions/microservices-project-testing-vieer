package com.mpv2.ratingMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RatingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingMicroserviceApplication.class, args);
	}

}
