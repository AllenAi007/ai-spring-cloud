package com.ai.spring.cloud.zuul.service;

/**
 * Authentication and Authorization Service
 */
public interface AuthService {


    /**
     * Return an JWT Token
     * @param userId
     * @param password
     * @return
     */
    default String login(String userId, String password) {
        return "";
    }

    /**
     * Default do nothing
     * @param userId
     * @return
     */
    default String createToken(String userId) {
        return "";
    }

    /**
     * Authentication,
     * @param authToken Auth Token
     * @return true if authorized
     */
    public boolean verify(String authToken);


}
