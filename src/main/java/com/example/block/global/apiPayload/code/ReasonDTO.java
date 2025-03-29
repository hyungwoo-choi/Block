package com.example.block.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReasonDTO {
    private HttpStatus httpStatus;
    private String message;
    private String code;
    private boolean isSuccess;
}