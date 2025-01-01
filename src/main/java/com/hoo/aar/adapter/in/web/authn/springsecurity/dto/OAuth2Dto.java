package com.hoo.aar.adapter.in.web.authn.springsecurity.dto;

import com.hoo.aar.adapter.out.persistence.entity.SnsAccountJpaEntity;

import java.util.Map;

public record OAuth2Dto() {
    public record KakaoUserInfo(
            String id,
            KakaoAccount kakao_account
    ) {
        public record KakaoAccount(
                String email,
                Boolean has_email,
                Boolean is_email_valid,
                Boolean is_email_verified,
                KakaoAccount.Profile profile
        ) {
            public record Profile(
                    String nickname,
                    Boolean is_default_nickname
            ) {
            }
        }
    }
}
