package com.example.block.global.apiPayload.code;

import com.example.block.global.apiPayload.code.status.ErrorStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public class ExceptionDto {

    @Schema(name = "code", description = "에러 코드")
    private final String code;

    @Schema(name = "message", description = "에러 메시지")
    private final String message;

    public ExceptionDto(ErrorStatus errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ExceptionDto of(ErrorStatus errorCode) {
        return new ExceptionDto(errorCode);
    }
}
