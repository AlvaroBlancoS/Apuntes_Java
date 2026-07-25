package springboot.token.user.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import springboot.security.BearerTokenFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

        private final BearerTokenFilter bearerTokenFilter;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/swagger-ui/**",
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui.html")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/users/**")
                                                .hasAnyRole("USER", "ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/users/**")
                                                .hasAnyRole("ADMIN", "USER")
                                                .requestMatchers(HttpMethod.PUT, "/api/users/**")
                                                .hasAnyRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .addFilterBefore(
                                                bearerTokenFilter,
                                                UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
