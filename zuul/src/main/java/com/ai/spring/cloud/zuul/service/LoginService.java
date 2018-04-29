package com.ai.spring.cloud.zuul.service;

import com.ai.spring.cloud.zuul.model.User;

public interface LoginService {

    /**
     *
     * @param username
     * @param password
     * @return return null means login failed.
     */
    public User doLogin(String username, String password);

    /**
     * Verify each service to see whether they are authorized or not, if not redirect to
     * @param username
     * @param token
     * @return
     */
    public boolean verify(String username, String token);

}
