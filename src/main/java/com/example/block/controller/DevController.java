package com.example.block.controller;

import com.example.block.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dev")
@Tag(name = "개발 전용", description = "test API")
public class DevController {

    private final AuthService authService;
    @Operation(
            summary = "Ping 테스트",
            responses = @ApiResponse(responseCode = "200", description = "pong을 반환합니다.")
    )
    @GetMapping("ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("pong" + authService.getUserIdFromSecurity());
    }
}
