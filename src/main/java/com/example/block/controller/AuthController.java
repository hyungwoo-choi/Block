package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.dto.*;
import com.example.block.global.constants.Constants;
import com.example.block.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(Constants.API_PREFIX + "/auth")
@Tag(name = "Auth", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "PoviderId를 통해서 로그인을 한다.[테스트 용]")
    @Schema(name = "login", description = "로그인")
    public ApiResponse<?> login(@RequestBody UserLoginDto userloginDto) {
        return ApiResponse.onSuccess(authService.login(userloginDto));
    }

    @Operation(summary = "카카오 로그인", description = "카카오 로그인 handler의 콜백 메서드이다.")
    @GetMapping("/oauth2/kakao")
    public ApiResponse<KakaoLoginResponse> loginKakao(@RequestParam(name = "accessToken") String accessToken,
                                                      @RequestParam(name = "refreshToken") String refreshToken, @RequestParam(name = "providerId") Long providerId) {

        return ApiResponse.onSuccess(KakaoLoginResponse.of(accessToken,refreshToken,providerId));
    }

    @Operation(
            summary = "회원가입",
            description = "회원가입 후 토큰을 반환합니다. 카카오 로그인인 경우는 카카오 인증을 먼저 받고 실행해주세요 platform은 kakao혹은 general을 입력해주세요"
    )
    @PostMapping("/sign-up")
    public ApiResponse<?> signUp(@RequestBody SignUpRequest request) {

        return ApiResponse.onSuccess(authService.signUp(request));

    }

    @Operation(
            summary = "로그인",
            description = "로그인 후 토큰을 반환합니다."
    )
    @PostMapping("/sign-in")
    public ApiResponse<JwtTokenDto> signIn(@RequestBody SignInRequest request) {

        return ApiResponse.onSuccess(authService.signIn(request));
    }

}
