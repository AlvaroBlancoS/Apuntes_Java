package springboot.token.mail;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springboot.entity.Mail;
import springboot.repository.EmailRepository;

@SpringBootApplication
@EnableFeignClients
// @EntityScan({"springboot.feignclient"})
// @ComponentScan(basePackages = {"springboot.feignclient"})
// @EnableJpaRepositories({"springboot.feignclient"})
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(
        EmailRepository emailRepository
    ) {
        return args -> {
            Mail mail = emailRepository.save(new Mail(null, "admin@admin.com", "Admin email accessing token"));
            System.out.println("Mail saved successfully with ID: " + mail.getId());
        };
    }

}
