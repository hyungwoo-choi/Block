package com.example.block.security.handler.signout;

import com.example.block.repository.UserRepository;
import com.example.block.security.info.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomSignOutProcessHandler implements LogoutHandler {

    private final UserRepository userRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 사용자가 null인 경우 아무 작업도 하지 않고 메서드를 종료한다.
        if (authentication == null) {
            return;
        }

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        userRepository.updateRefreshTokenAndLoginStatus(userPrincipal.getId(), null, false);
    }
}
