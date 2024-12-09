package com.practice.springData2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.practice.springData2.domain.product.Product;
import com.practice.springData2.repository.ProductRepository;
import com.practice.springData2.repository.UserRepository;

@SpringBootApplication
@EntityScan({ "com.practice.springData2.domain" })
@ComponentScan(basePackages = { "com.practice.springData2" })
@EnableJpaRepositories({ "com.practice.springData2" })
public class SpringData2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringData2Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			ProductRepository productRepository,
			UserRepository userRepository
			) {
		return args ->{
//			Product product1 = productRepository.save(new Product(null,"Guitarra",80.95,"Spain"));
//			Product product2 = productRepository.save(new Product(null,"Chaleco",45.95,"Ecuador"));
//			Product product3 = productRepository.save(new Product(null,"Sombrero",200.00,"German"));
//			List<Product> products = new ArrayList<Product>();
//			products.add(product1);
//			products.add(product2);
//			products.add(product3);
//			for (Product product : products) {
//				System.out.println(product.toString());
//			}
			List<Product>products = List.of(
					new Product(null,"Guitarra",80.95,"Spain"),
					new Product(null,"Chaleco",45.95,"Ecuador"),
					new Product(null,"Sombrero",200.00,"German")
					);
			for (Product product : products) {
				if (productRepository.findByNameProduct(product.getNameProduct()).isEmpty()) {
					productRepository.save(product);
				}
			}
		};
	}
	
	
}
