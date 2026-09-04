package springboot.token.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "springboot.entity", "springboot.security.entity", "springboot.token.token.entity" })
@EnableJpaRepositories(basePackages = { "springboot.repository", "springboot.security.repository", "springboot.token.token.repository" })
@ComponentScan(basePackages = { "springboot.token.token", "springboot.util.exception" })
public class TokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class, args);
    }
}
