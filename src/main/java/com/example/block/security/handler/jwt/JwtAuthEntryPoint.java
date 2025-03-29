package com.example.block.security.handler.jwt;

import com.example.block.global.apiPayload.code.ErrorReasonDTO;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.security.info.AbstractAuthenticationFailure;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthEntryPoint extends AbstractAuthenticationFailure implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ErrorReasonDTO errorReason = (ErrorReasonDTO) request.getAttribute("exception");
        log.info("errorReason: {}", errorReason);
        if (errorReason == null) {
            errorReason = ErrorReasonDTO.builder()
                    .message("Endpoint not found")
                    .code("NOT_FOUND_END_POINT")
                    .isSuccess(false)
                    .httpStatus(HttpStatus.NOT_FOUND) // HttpStatus를 설정합니다.
                    .build();
        }

        setErrorResponse(response, errorReason);
    }

}
