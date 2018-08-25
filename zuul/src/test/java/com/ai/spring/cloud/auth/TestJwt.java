package com.ai.spring.cloud.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;


/**
 * Test JSON Web token
 */
public class TestJwt {

    private static final Logger log = LoggerFactory.getLogger(TestJwt.class);

    @Test
    public void simpleHmac() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create().withIssuer("auth0").sign(algorithm);
        log.info("token -> {}", token);
        Assert.assertEquals(3, token.split("\\.").length);
    }

    @Test
    public void hmacWithIssueAt() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create().withIssuer("auth0")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date())
                .withNotBefore(new Date())
                .sign(algorithm);
        log.info("token -> {}", token);
        Assert.assertEquals(3, token.split("\\.").length);
    }

    @Test
    public void simpleHmacDecode() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create().withIssuer("auth0").sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(token);
        String header = jwt.getHeader();
        String payload = jwt.getPayload();
        String signature = jwt.getSignature();
        String jwtToken = jwt.getToken();
        log.info("Header:{}, Payload:{}, Signature:{}, Token:{}", header, payload, signature, jwtToken);
        Assert.assertEquals(jwtToken, token);
    }

    @Test
    public void issueAtBeforeToday() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        String token = JWT.create().withIssuer("auth0")
                .withIssuedAt(calendar.getTime())
//                .withExpiresAt(new Date())
//                .withNotBefore(new Date())
                .sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptLeeway(1)   //1 sec for nbf and iat
//                .acceptExpiresAt(5)   //5 secs for exp
                .build();

        verifier.verify(token);
//        Thread.currentThread().sleep(10000);
//        verifier.verify(token);

    }

    @Test(expected = JWTVerificationException.class)
    public void issueAtAfterToday() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        String token = JWT.create().withIssuer("auth0")
                .withIssuedAt(calendar.getTime())
//                .withExpiresAt(new Date())
//                .withNotBefore(new Date())
                .sign(algorithm);
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptLeeway(1)   //1 sec for nbf and iat
//                .acceptExpiresAt(5)   //5 secs for exp
                .build();

        verifier.verify(token);
    }


    /**
     *
     */
    public void testHack() {
        /**
         * Hacked Json
         * Header:
         * {
         *   "typ": "JWT",
         *   "alg": "HS256"
         * }
         * payload:
         * {
         *   "iss": "auth0",
         *   "exp": 1525324951,
         *   "userId": "ai"
         * }
         * Signature:
         * HMACSHA256(
         *   base64UrlEncode(header) + "." +
         *   base64UrlEncode(payload),
         *
         * ) secret base64 encoded
         */
        /**
         * {
         *   "typ": "JWT",
         *   "alg": "HS256"
         * }
         */
        String know = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTUyNTMyNDk1MSwidXNlcklkIjoiYWkifQ.Htn-D4cZM0okYBh33EwOxkVbDM0GTwe2TvtezP9otbU";


    }
}
