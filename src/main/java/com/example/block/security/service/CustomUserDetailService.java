package com.example.block.security.service;

import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.GeneralException;
import com.example.block.repository.UserRepository;
import com.example.block.security.info.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService {

    private final UserRepository userRepository;

    public UserDetails loadUserById(Integer id) {
        UserRepository.UserSecurityForm userSecurityForm = userRepository.findSecurityFormById(id)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_LOGIN_USER));

        return UserPrincipal.createByUserSecurityForm(userSecurityForm);
    }
}
