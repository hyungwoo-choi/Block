package com.example.block.oauth.info;

import com.example.block.global.constants.Constants;
import com.example.block.security.enums.ERole;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Builder
@RequiredArgsConstructor
public class KakaoMemberDetails implements OAuth2User {

    @Getter
    private final Long id; //카카오 사용자 provider ID
    @Getter
    private final ERole role;
    //사용자의 권한 정보르 저장한다. (Role, Permission)
    private final List<? extends GrantedAuthority> authorities;
    //사용자의 추가 속성 정보를 저장한다.
    private final Map<String, Object> attributes;


    public static KakaoMemberDetails createByProviderId(Long providerId, OAuth2User oAuth2User){
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(Constants.USER_ROLE));
        return KakaoMemberDetails.builder()
                .id(providerId)
                .authorities(authorities)
                .attributes(oAuth2User.getAttributes())
                .build();
    }


    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getName() {
        return "Kakao Member Details";
    }
}
