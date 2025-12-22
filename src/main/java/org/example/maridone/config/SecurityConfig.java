package org.example.maridone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    //SECURITYFILTERCHAIN
    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/html/login.html", "/html/index.html", "/css/**", "/js/**", "/assets/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/html/login.html")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/html/index.html")
                        .failureUrl("/html/login.html")
                        .permitAll()
                )
                .build();
    }

//    @Bean
//    AuthenticationSuccessHandler authenticationSuccessHandler() {
//
//    }
}
