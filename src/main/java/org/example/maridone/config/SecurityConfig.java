package org.example.maridone.config;

import org.example.maridone.exception.NoRoleAssignedException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

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
        //Todo: OWASP top 10
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/html/login.html", "/html/index.html", "/css/**", "/js/**", "/assets/**")
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
                case "ROLE_EMPLOYEE": {
                    url = "/html/employee/ss_dashboard.html";
                    break;
                }
                case "ROLE_ACCOUNTING": {
                    url  = "/html/accounting/ss_dashboard.html";
                    break;
                }
                case "ROLE_MANAGEMENT": {
                    url = "/html/management/ss_dashboard.html";
                    break;
                }
                case "ROLE_HR": {
                    url = "/html/hr/ss_dashboard.html";
                    break;
                }
                case "ROLE_MANAGER": {
                    url = "/html/manager/ss_dashboard.html";
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
