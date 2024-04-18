package com.ums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    private JWTresponsefilter jwTresponsefilter;

    public SecurityConfig(JWTresponsefilter jwTresponsefilter) {
        this.jwTresponsefilter = jwTresponsefilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwTresponsefilter, AuthorizationFilter.class);
        http.authorizeHttpRequests().
        requestMatchers("/api/v1/auth/addUser/users","/api/v1/auth/addUser","/api/v1/auth/login").permitAll()
                .requestMatchers("/api/v1/auth/token").hasRole("ADMIN")
                .requestMatchers("/api/v1/auth/profile").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated();
        return http.build();
    }



}
