package com.example.block.global.apiPayload.exception.handler;

import com.example.block.global.apiPayload.code.BaseErrorCode;
import com.example.block.global.apiPayload.exception.GeneralException;

public class RatingHandler extends GeneralException {

    public RatingHandler(BaseErrorCode errorCode) {super(errorCode);}
}
