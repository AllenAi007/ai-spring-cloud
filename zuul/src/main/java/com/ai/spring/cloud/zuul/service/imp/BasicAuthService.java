package com.ai.spring.cloud.zuul.service.imp;

import com.ai.spring.cloud.zuul.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Basic AUTH, using Base64(username:password)
 */
@Service
public class BasicAuthService implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(BasicAuthService.class);

    /**
     * total 128 client users
     */
    private Map<String, String> userAuthCache = new ConcurrentHashMap<>(128);

    private Map<String, String> userDataBase = new HashMap<>();

    {
        userDataBase.put("aihua", "abc123");
        userDataBase.put("admin", "admin");
    }

    /**
     * Sample Header -> Authentication: BASIC YWlodWE6YWJjMTIz
     *
     * @param authToken Auth Token
     * @return
     */
    @Override
    public boolean verify(String authToken) {
        if (StringUtils.isEmpty(authToken)) {
            // empty or null token, return false
            return false;
        }

        try {
            String[] auths = authToken.split(" ");
            String authType = auths[0];
            String authSecret = new String(Base64.getDecoder().decode(auths[1].getBytes()));
            String username = authSecret.split(":")[0];
            String password = authSecret.split(":")[1];
            log.info("Authentication: {}, authtype: {}, username: {}, password:{}", authToken, authType, username, password);

            // step 1. check cache to see whether logged in already
            if (userAuthCache.containsKey(username) && userAuthCache.get(username).equals(authSecret)) {
                return true;
            }
            // step 2. check db whether user is there or not.
            if (userDataBase.containsKey(username) && userDataBase.get(username).equals(password)) {
                // put into cache and return true
                userAuthCache.put(username, Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
                return true;
            }
            // cant find
            return false;

        } catch (Exception e) {
            // exception caused, return false;
            log.info("auth failed : " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        String secret = "admin:admin";
        System.out.println(Base64.getEncoder().encodeToString(secret.getBytes()));
    }
}
