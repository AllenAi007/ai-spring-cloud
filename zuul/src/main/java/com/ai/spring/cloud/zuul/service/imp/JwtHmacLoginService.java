package com.ai.spring.cloud.zuul.service.imp;

import com.ai.spring.cloud.zuul.model.User;
import com.ai.spring.cloud.zuul.service.LoginService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;


/**
 * JWT HMAC Login Service for login
 */
public class JwtHmacLoginService implements LoginService {
    @Override
    public User login(String username, String password) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create().withIssuer("auth0").sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean verify(String username, String token) {
        return false;
    }
}
