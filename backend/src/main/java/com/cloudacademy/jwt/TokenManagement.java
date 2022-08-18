package com.cloudacademy.jwt;

import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cloudacademy.Unauthorized;

public class TokenManagement {
    private static String SECRET = "abx123";

    public static String createJWTToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            String token = JWT.create()
                .withIssuer("cloudacademy")
                .withClaim("name", username)
                .sign(algorithm);

            System.out.println("jwt: " + token);

            return token;
        } catch (JWTCreationException exception){
            return "failed_token";
        }
    }
    
    public static String authenticateJWTToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("cloudacademy")
                .build();
            DecodedJWT jwt = verifier.verify(token);
            var name = jwt.getClaim("name");
            System.out.println("name: " + name);
            return name.asString();
        } catch (JWTVerificationException e){
            e.printStackTrace();
            throw new Unauthorized(e.getMessage());
        }
    }
}
