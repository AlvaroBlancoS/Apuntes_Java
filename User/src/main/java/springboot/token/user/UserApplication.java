package springboot.token.user;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import springboot.entity.User;
import springboot.repository.EmailRepository;
import springboot.token.user.repository.UserRepository;

@SpringBootApplication
// @EntityScan({ "com.practice.springData2.domain" })
// @ComponentScan(basePackages = { "com.practice.springData2" })
// @EnableJpaRepositories({ "com.practice.springData2" })
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
		UserRepository userRepository,
		EmailRepository emailRepository
	) {
		return args -> {
			UUID mailId = emailRepository.findByMail("admin@admin.com").orElseThrow(() -> new RuntimeException("Email not found")).getId();
			User user = userRepository.save(new User(null, "admin", "admin", mailId));
			System.out.println("User saved successfully with ID: " + user.getId());
		};
	}

}
