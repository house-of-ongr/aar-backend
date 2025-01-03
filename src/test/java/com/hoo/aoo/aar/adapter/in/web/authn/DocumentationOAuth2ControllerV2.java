package com.hoo.aoo.aar.adapter.in.web.authn;

import com.hoo.aoo.aar.adapter.in.web.authn.security.SNSLoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentationOAuth2ControllerV2 {

    @GetMapping("/aar/authn/login/kakao/v2")
    public ResponseEntity<SNSLoginResponse> kakaoLogin() {
        return new ResponseEntity<>(new SNSLoginResponse(
                "leaf",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
                "kakao",
                false), HttpStatus.OK);
    }
}
