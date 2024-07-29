package com.futbol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class FutbolV2bEquipoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolV2bEquipoServiceApplication.class, args);
	}
/*
	@Bean(name = "equipoRestTemplate")
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	*/

}
