package com.futbol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FutbolV2cJugadorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolV2cJugadorServiceApplication.class, args);
	}

}
