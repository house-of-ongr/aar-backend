package com.hoo.aar.application.config;

import com.hoo.aar.adapter.in.web.authn.springsecurity.AarSecurityConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = {SecurityMockMvcConfig.class, AarSecurityConfig.class})
@ExtendWith(RestDocumentationExtension.class)
@WebAppConfiguration
@WebMvcTest
@AutoConfigureRestDocs
public @interface DocumentationTest {
}
