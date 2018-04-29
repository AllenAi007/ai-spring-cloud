package com.ai.spring.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Configuration Application
 */
@EnableConfigServer
@SpringBootApplication
@RestController
public class ConfigApplication {

    @Value("${info.foo}")
    String bar;

    @GetMapping("/") String home(){
        return bar;
    }
    /**
     * Main entrance
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

}
