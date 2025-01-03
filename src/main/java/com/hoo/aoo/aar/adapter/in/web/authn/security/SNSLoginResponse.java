package com.hoo.aoo.aar.adapter.in.web.authn.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoo.aoo.aar.domain.SnsAccount;

import java.util.HashMap;
import java.util.Map;

public record SNSLoginResponse(
        String nickname,
        String accessToken,
        String provider,
        Boolean isFirstLogin
) {

    public static SNSLoginResponse from(Map<String, Object> attributes) {
        return new SNSLoginResponse(
                (String) attributes.get("nickname"),
                (String) attributes.get("accessToken"),
                (String) attributes.get("provider"),
                (Boolean) attributes.get("isFirstLogin"));
    }

    public static SNSLoginResponse of(SnsAccount accountEntity, String accessToken, boolean isFirstLogin) {

        String nickname = isFirstLogin?
                accountEntity.getNickname() : accountEntity.getUser().getNickname();

        return new SNSLoginResponse(
                nickname,
                accessToken,
                accountEntity.getSnsDomain().name(),
                isFirstLogin);
    }

    @JsonIgnore
    public Map<String, Object> getAttributes() {

        Map<String, Object> ret = new HashMap<>();

        ret.put("nickname", nickname);
        ret.put("accessToken", accessToken);
        ret.put("provider", provider);
        ret.put("isFirstLogin", isFirstLogin);

        return ret;
    }
}
