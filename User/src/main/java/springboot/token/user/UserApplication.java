package springboot.token.user;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springboot.entity.RoleType;
import springboot.entity.User;
import springboot.repository.EmailRepository;
import springboot.repository.UserRepository;
import springboot.security.BearerTokenFilter;
import springboot.util.exception.GlobalExceptionHandler;

@SpringBootApplication
@EntityScan(basePackages = { "springboot.entity", "springboot.security.entity" })
@EnableJpaRepositories(basePackages = { "springboot.repository", "springboot.security.repository" })
@ComponentScan(basePackages = { "springboot.token.user", "springboot.mapper" })
@Import({ BearerTokenFilter.class, GlobalExceptionHandler.class })
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
	private final String userName = "user";
	private final String adminName = "admin";
	private static final Logger LOGGER = LoggerFactory.getLogger(UserApplication.class);

	@Bean
	CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			EmailRepository emailRepository) {
		return args -> {
			if (!userRepository.findByName(userName).isPresent() && !userRepository.findByName(adminName).isPresent()) {
				UUID mailId = emailRepository.findByMail("user@user.com")
						.orElseThrow(() -> new RuntimeException("Email User not found")).getId();
				userRepository.save(User.builder().name(userName).password("user").mailId(mailId).roleType(RoleType.USER).build());

				UUID mailIdAdmin = emailRepository.findByMail("admin@admin.com")
						.orElseThrow(() -> new RuntimeException("Email Admin not found")).getId();
				userRepository.save(User.builder().name(adminName).password("admin").mailId(mailIdAdmin).roleType(RoleType.ADMIN).build());
				
				LOGGER.info("User and Admin have been created successfully.");
			}

			LOGGER.info("User and Admin already exist in the database.");

		};
	}

}
