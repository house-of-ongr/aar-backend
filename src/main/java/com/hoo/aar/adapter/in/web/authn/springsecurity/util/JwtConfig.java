package com.hoo.aar.adapter.in.web.authn.springsecurity.util;

import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtConfig {

    private final MACSigner macSigner;
    private final JwtAttribute jwtAttribute;

    public JwtConfig(@Value("${authn.jwt.secret}") String secret,
                     @Value("${authn.jwt.issuer}") String issuer,
                     @Value("${authn.jwt.expire}") Long expire
    ) throws KeyLengthException {
        this.macSigner = new MACSigner(secret);
        this.jwtAttribute = new JwtAttribute(secret, issuer, expire);
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(macSigner.getSecretKey()).build();
    }

    @Bean
    JwtUtil jwtUtil() {
        return new JwtUtil(macSigner, jwtAttribute);
    }
}
