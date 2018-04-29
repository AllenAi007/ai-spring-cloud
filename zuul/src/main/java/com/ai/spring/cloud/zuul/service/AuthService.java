package com.ai.spring.cloud.zuul.service;

/**
 * Authentication and Authorization Service
 */
public interface AuthService {

    public static final String AUTHENTICATION = "Authentication";

    /**
     * Authentication,
     * @param authToken Auth Token
     * @return true if authorized
     */
    public boolean verify(String authToken) ;


}
