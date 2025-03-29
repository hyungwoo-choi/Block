package com.example.block.oauth.info;

import com.example.block.global.constants.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.Map;

public class KakaoUserInfo {

    @Getter
    private Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Long getId(){
        return (Long) attributes.get(Constants.KAKAO_ID);
    }

    public String getEmail() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReferencer = new TypeReference<Map<String, Object>>() {
        };

        Object kakaoAccount = attributes.get(Constants.KAKAO_ACCOUNT);
        Map<String, Object> account = objectMapper.convertValue(kakaoAccount, typeReferencer);

        return (String) account.get(Constants.KAKAO_EMAIL);
    }
}
