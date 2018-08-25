package com.ai.spring.cloud.zuul.service.imp;

import com.ai.spring.cloud.zuul.model.User;
import com.ai.spring.cloud.zuul.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class LoginServiceImp implements LoginService {

    @Override
    public User login(String username, String password) {
        if(username == "AIHUA" && password == "abc123") {
            return new User(username, password);
        }
        return null;
    }

    @Override
    public boolean verify(String username, String token) {
        return username == "AIHUA" && token == Base64.getEncoder().encodeToString("AIHUA:abc123".getBytes());
    }
}
