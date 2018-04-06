package com.ai.spring.cloud.myservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * My Service Application
 */
@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class MyServiceApplication {

    @GetMapping("/")
    String home() {
        return hello();
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello Spring cloud!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyServiceApplication.class, args);
    }
}
