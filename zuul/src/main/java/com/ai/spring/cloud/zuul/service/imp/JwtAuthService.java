package com.ai.spring.cloud.zuul.service.imp;

import com.ai.spring.cloud.zuul.service.AuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.http.auth.AUTH;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * JWT authorization service
 */
public class JwtAuthService implements AuthService {

    private static final String AUTH_TYPE = "Bearer";
    private static final String CLAIM_USERID = "userId";

    private int expire = 2;

    private Cache<String, String> authCache = CacheBuilder.newBuilder()
            .expireAfterWrite(expire, TimeUnit.HOURS)
            .build();

    private Map<String, String> userDB = new ConcurrentHashMap<>();

    private final String secret;

    private final Algorithm algorithm;

    public JwtAuthService() {
        secret = UUID.randomUUID().toString();
        try {
            algorithm = Algorithm.HMAC256(secret);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Initiate JWT algorithm failed", e);
        }

        // init some user data
        userDB.put("ai", "abc123");
        userDB.put("hua", "abc123");
        userDB.put("allen", "abc123");
        userDB.put("admin", "admin");
    }

    @Override
    public String login(String userId, String password) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(password)) {
            throw new RuntimeException("Empty user id or password");
        }
        if (password.equals(userDB.get(userId))) {
            //login successfully, generate token and put into cache
            String token = createToken(userId);
            authCache.put(userId, AUTH_TYPE + " " + token);
            return AUTH_TYPE + " " + token;
        } else {
            throw new RuntimeException("Wrong user id or password");
        }

    }

    @Override
    public String createToken(String userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.HOUR) + 2);
        return JWT.create()
                .withIssuer("auth0")
                .withClaim(CLAIM_USERID, userId)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    /**
     * Bearer
     *
     * @param authToken Auth Token
     * @return
     */
    @Override
    public boolean verify(String authToken) throws RuntimeException {
        if (StringUtils.isEmpty(authToken)) {
            throw new RuntimeException("Empty token");
        }
        String authType = authToken.split(" ")[0];
        String token = authToken.split(" ")[1];

        if (!AUTH_TYPE.equals(authType)) {
            throw new RuntimeException("Un-Expected authorization type:" + authType);
        }
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String userId = decodedJWT.getClaim(CLAIM_USERID).asString();
            if (StringUtils.isEmpty(userId)) {
                throw new RuntimeException("No user id found for given token");
            }
            String cachedToken = authCache.getIfPresent(userId);
            if (!decodedJWT.getToken().equals(cachedToken)) {
                throw new RuntimeException("Token doesn't match");
            }
            return true;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

    /**
     * Generate a secret
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}
