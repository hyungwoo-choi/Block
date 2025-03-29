package com.example.block.oauth.service;

import com.example.block.domain.User;
import com.example.block.global.constants.Constants;
import com.example.block.oauth.info.KakaoMemberDetails;
import com.example.block.oauth.info.KakaoUserInfo;
import com.example.block.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoMemberDetailService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {


        OAuth2User oAuth2User = super.loadUser(userRequest);
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(oAuth2User.getAttributes());

        User user;
        Optional<User> existingUser = userRepository.findBySerialId(kakaoUserInfo.getId());

        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            user = userRepository.save(User.builder()
                    .serialId(kakaoUserInfo.getId())
                    .build());
            //얘는 항상 true인데 이경우에만 false로 바꿔준다. (kakao로그인 시에만)
            user.setIsNewUser(false);
            user.setPlatform(Constants.PLATFORM_KAKAO);
        }

        return KakaoMemberDetails.createByProviderId(user.getSerialId(), oAuth2User);
    }
}
