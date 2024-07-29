package com.futbol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FutbolV2aDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolV2aDiscoveryServiceApplication.class, args);
	}

}
