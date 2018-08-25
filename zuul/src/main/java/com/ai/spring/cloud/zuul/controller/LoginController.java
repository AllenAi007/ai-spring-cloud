package com.ai.spring.cloud.zuul.controller;

import com.ai.spring.cloud.zuul.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Login Controller
 */
@Controller
public class LoginController {

    @Autowired
    AuthService authService;

    @GetMapping("/")
    public String home(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "/login.html";
    }

    @GetMapping("/sso/login")
    public String login(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "/login.html";
    }

    @PostMapping("/sso/doLogin")
//    @ResponseBody
    public ResponseEntity doLogin(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        try {
            return ResponseEntity.ok(authService.login(userId, password));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
