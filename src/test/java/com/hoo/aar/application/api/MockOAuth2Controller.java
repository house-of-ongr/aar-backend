package com.hoo.aar.application.api;

import com.hoo.aar.domain.authn.dto.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockOAuth2Controller {

    @GetMapping("/authn/kakao")
    public ResponseEntity<Login.Response> kakaoLogin() {
        return new ResponseEntity<>(new Login.Response("leaf",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
                "kakao",false), HttpStatus.OK);
    }
}
