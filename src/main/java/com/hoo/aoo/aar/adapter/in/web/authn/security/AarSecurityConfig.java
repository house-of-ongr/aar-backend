package com.hoo.aoo.aar.adapter.in.web.authn.security;

import com.hoo.aoo.aar.adapter.in.web.authn.security.handler.OAuth2SuccessHandler;
import com.hoo.aoo.aar.adapter.in.web.authn.security.service.OAuth2UserServiceDelegator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class AarSecurityConfig {

    @Bean
    public SecurityFilterChain aarFilterChain(HttpSecurity http, OAuth2UserServiceDelegator userService, OAuth2SuccessHandler oAuth2SuccessHandler, JwtDecoder jwtDecoder) throws Exception {
        return http
                .securityMatcher("/aar/**")
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(authorization ->
                                authorization.baseUri("/aar/authn/login/**"))
                        .redirectionEndpoint(redirection ->
                                redirection.baseUri("/aar/authn/code/**"))
                        .userInfoEndpoint(userInfo ->
                                userInfo.userService(userService))
                        .successHandler(oAuth2SuccessHandler))

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder)))

                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(GET,
                                        "/aar/authn/login/**",
                                        "/aar/authn/kakao/callback")
                                .permitAll()

                                .requestMatchers(POST,
                                        "/aar/authn/regist")
                                .hasRole("TEMP_USER")

                                .anyRequest().authenticated())

                .exceptionHandling(handler ->
                        handler.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("role");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

}
