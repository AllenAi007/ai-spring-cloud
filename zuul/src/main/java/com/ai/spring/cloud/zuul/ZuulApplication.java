package com.ai.spring.cloud.zuul;

import com.ai.spring.cloud.zuul.filter.OkHttpRoutingFilter;
import com.ai.spring.cloud.zuul.filter.QueryParamPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@EnableZuulProxy
@SpringBootApplication
@Controller
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "login";
    }

    @Bean
    public OkHttpRoutingFilter okHttpRoutingFilter() {
        return new OkHttpRoutingFilter();
    }

    @Bean
    public QueryParamPreFilter queryParamPreFilter() {
        return new QueryParamPreFilter();
    }

}
