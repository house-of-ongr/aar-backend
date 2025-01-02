package com.hoo.aar.adapter.in.web.authn.security.service;

import com.hoo.aar.adapter.in.web.authn.security.SNSLoginResponse;
import com.hoo.aar.adapter.in.web.authn.security.dto.OAuth2Dto;
import com.hoo.aar.adapter.in.web.authn.security.jwt.JwtUtil;
import com.hoo.aar.adapter.out.persistence.entity.SnsAccountJpaEntity;
import com.hoo.aar.adapter.out.persistence.mapper.SnsAccountMapper;
import com.hoo.aar.application.port.out.database.LoadSnsAccountPort;
import com.hoo.aar.application.port.out.database.SaveSnsAccountPort;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class KakaoLoadUserService implements LoadUserService {

    private final Gson gson = new Gson();
    private final LoadSnsAccountPort loadSnsAccountPort;
    private final SaveSnsAccountPort saveSnsAccountPort;
    private final SnsAccountMapper snsAccountMapper;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public OAuth2User load(OAuth2User user) {

        OAuth2Dto.KakaoUserInfo userInfo = gson.fromJson(gson.toJsonTree(user.getAttributes()), OAuth2Dto.KakaoUserInfo.class);

        SNSLoginResponse response = loadSnsAccountPort.load(userInfo.id()).map(entity -> SNSLoginResponse.of(entity, jwtUtil.getAccessToken(entity), false))

                .orElseGet(() -> {
                    SnsAccountJpaEntity newEntity = saveSnsAccountPort.save(snsAccountMapper.kakaoUserToSnsAccount(userInfo));
                    return SNSLoginResponse.of(newEntity, jwtUtil.getAccessToken(newEntity), true);
                });

        return new DefaultOAuth2User(user.getAuthorities(), response.getAttributes(), "username");
    }
}