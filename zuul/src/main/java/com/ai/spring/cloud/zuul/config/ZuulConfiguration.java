package com.ai.spring.cloud.zuul.config;

import com.ai.spring.cloud.zuul.filter.AuthFilter;
import com.ai.spring.cloud.zuul.service.AuthService;
import com.ai.spring.cloud.zuul.service.imp.JwtAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {

    @Bean
    public AuthFilter queryParamPreFilter() {
        return new AuthFilter();
    }

    @Bean
    public AuthService authService() {
        return new JwtAuthService();
    }
}
