package com.hoo.aar.adapter.in.web.authn.security.jwt;

import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;

import java.security.SecureRandom;

public class MockJwtUtil {

    public static JwtUtil getJwtUtil() {
        byte[] secretKey = new byte[32];
        new SecureRandom().nextBytes(secretKey);

        try {
            return new JwtUtil(new MACSigner(secretKey),
                    new JwtAttribute(
                            new String(secretKey),
                            "mock_jwt_util",
                            10000L
                    ));
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        }
    }
}