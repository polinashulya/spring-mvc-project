package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/", "/home", "/login", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/employees/**").hasAnyRole("EMPLOYEE", "CLIENT", "ADMIN")
//                        .requestMatchers("/clients/**").hasAnyRole("EMPLOYEE", "CLIENT", "ADMIN")
//                        .anyRequest().authenticated()
//                )
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/", "/home", "/login", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll() // Allow access to public resources
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // Admin-only access
//                        .requestMatchers("/employees/**").hasAnyRole("EMPLOYEE", "CLIENT", "ADMIN") // Employee-related pages
//                        .requestMatchers("/clients/**").hasAnyRole("EMPLOYEE", "CLIENT", "ADMIN") // Client-related pages
//                        .anyRequest().authenticated() // All other pages require authentication
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Custom login page
//                        .loginProcessingUrl("/login") // Login form action URL
//                        .defaultSuccessUrl("/", true) // Redirect to home page on successful login
//                        .failureUrl("/login?error") // Redirect to login page with error message on failure
//                        .usernameParameter("username") // Username parameter name
//                        .passwordParameter("password") // Password parameter name
//                        .permitAll() // Allow access to login page without authentication
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // URL to trigger logout
//                        .logoutSuccessUrl("/login?logout") // URL to redirect to on successful logout
//                        .permitAll() // Allow access to logout URL without authentication
//                )
//                .csrf(csrfConfigurer -> csrfConfigurer.disable());  // Disable CSRF protection for simplicity (consider enabling in production)

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
