package org.example.maridone.config;

import org.example.maridone.exception.NoRoleAssignedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private Environment env;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            http
                    .httpBasic(Customizer.withDefaults())
                    .csrf(customizer -> customizer.disable());
        }
        //Todo: OWASP top 10
        return http
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/html/*", "/css/**", "/js/**", "/assets/**")
                        .permitAll()
                        .requestMatchers("/html/accounting/**")
                        .hasRole("ACCOUNTING")
                        .requestMatchers("/html/employee/**")
                        .hasRole("EMPLOYEE")
                        .requestMatchers("/html/hr/**")
                        .hasRole("HR")
                        .requestMatchers("/html/management/**")
                        .hasRole("MANAGEMENT")
                        .requestMatchers("/html/manager/**")
                        .hasRole("MANAGER")
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/html/login.html")
                        .loginProcessingUrl("/process_login")
                        .failureUrl("/html/login.html?error")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()
                )

                .build();
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String authority =  authentication.getAuthorities().iterator().next().getAuthority();
            String url;

            switch(authority) {
                case "ROLE_EMPLOYEE":
                case "ROLE_MANAGEMENT":
                case "ROLE_HR":
                case "ROLE_MANAGER": {
                    url = "/html/ss_dashboard.html";
                    break;
                }
                case "ROLE_ACCOUNTING": {
                    url  = "/html/accounting/payroll_dashboard.html";
                    break;
                }
                case "ROLE_UNKNOWN": {
                    throw new NoRoleAssignedException("No Roles for this user. Please assign a Role.");
                }
                default: {
                    url = "/html/index.html?error=unknown+error";
                }
            }

            response.sendRedirect(url);
        };
    }

}
