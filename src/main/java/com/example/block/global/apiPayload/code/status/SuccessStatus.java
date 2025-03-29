package com.example.block.global.apiPayload.code.status;

import com.example.block.global.apiPayload.code.BaseCode;
import com.example.block.global.apiPayload.code.ReasonDTO;
import lombok.Getter;

@Getter
public enum SuccessStatus implements BaseCode {
    _OK("OK", "200", true);

    private final String message;
    private final String code;
    private final boolean isSuccess;

    private SuccessStatus(String message, String code, boolean isSuccess){
        this.message = message;
        this.code = code;
        this.isSuccess = isSuccess;
    }

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

}