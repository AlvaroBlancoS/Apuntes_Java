package springboot.token.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import springboot.entity.Mail;
import springboot.mapper.MailMapper;
import springboot.repository.EmailRepository;
import springboot.security.BearerTokenFilter;
import springboot.util.exception.GlobalExceptionHandler;

@SpringBootApplication
@EntityScan(basePackages = { "springboot.entity", "springboot.security.entity" })
@EnableJpaRepositories(basePackages = { "springboot.repository", "springboot.security.repository" })
@Import({ MailMapper.class, BearerTokenFilter.class, GlobalExceptionHandler.class })
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }
    private final String userEmail = "user@user.com";
    private final String adminEmail = "admin@admin.com";
    private static final Logger LOGGER = LoggerFactory.getLogger(MailApplication.class);

    @Bean
    CommandLineRunner commandLineRunner(
            EmailRepository emailRepository) {
        return args -> {

            if (!emailRepository.findByMail(userEmail).isPresent()
                    && !emailRepository.findByMail(adminEmail).isPresent()) {
                emailRepository.save(new Mail(null, userEmail, "User email accesing token"));
                emailRepository.save(new Mail(null, adminEmail, "Admin email accessing token"));
                LOGGER.info("User and Admin emails have been created successfully.");
            }

            LOGGER.info("User and Admin emails already exist in the database.");

        };
    }

}
