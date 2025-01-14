package com.example.demo.configurations;

import com.example.demo.util.JwtFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SecurityConfigBuilder {
    private AuthenticationConfiguration authenticationConfiguration;
    private JwtFilter jwtFilter;
    private UserDetailsService userDetailsService;

    public SecurityConfigBuilder setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
        return this;
    }

    public SecurityConfigBuilder setJwtFilter(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
        return this;
    }

    public SecurityConfigBuilder setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        return this;
    }

    public SecurityConfig createSecurityConfig() {
        return new SecurityConfig(authenticationConfiguration, jwtFilter, userDetailsService);
    }
}