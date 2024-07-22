package com.futbol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.futbol.control", "com.futbol.model", "com.futbol.service"})
public class FutbolV1Application {

	public static void main(String[] args) {
		SpringApplication.run(FutbolV1Application.class, args);
	}

}
