package com.example.block.global.apiPayload.code.status;

import com.example.block.global.apiPayload.code.BaseErrorCode;
import com.example.block.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
//  LIKE 관련 오류
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "EMAIL400", "Email 정보가 없습니다."),
    USERID_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER400", "USER 정보가 없습니다."),
    LIKE_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "USER400", "이미 좋아요를 하였습니다!"),
    LIKE_DOESNT_EXIST(HttpStatus.BAD_REQUEST, "USER400", "좋아요가 존재하지않습니다!"),

    //결제 관련 오류
    _PAY_CANCEL(HttpStatus.BAD_REQUEST,"PAY400","결제가 취소되었습니다."),
    _PAY_FAIL(HttpStatus.BAD_REQUEST,"PAY400","결제에 실패하였습니다."),
    _KAKAO_PAY_READY_FAIL(HttpStatus.BAD_REQUEST,"PAY400","카카오페이 준비에 실패하였습니다."),
    _ALREADY_PAID(HttpStatus.BAD_REQUEST,"PAY400","이미 결제한 리뷰입니다."),
    _NEED_PAY(HttpStatus.BAD_REQUEST,"PAY400","결제가 필요합니다."),
    //리뷰 관련
    _REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,"REVIEW404","리뷰가 존재하지 않습니다."),
    _REVIEW_ALREADY_EXIST(HttpStatus.NOT_FOUND,"REVIEW404","리뷰가 이미 존재합니다."),
    //유저 관련
    _USER_NOT_FOUND(HttpStatus.NOT_FOUND,"USER404","유저가 존재하지 않습니다."),
    EXIST_EMAIL( HttpStatus.CONFLICT, "EMAIL405", "이미 존재하는 이메일입니다."),
    NOT_FOUND_PASSWORD(HttpStatus.NOT_FOUND,"40405" , "비밀번호가 일치하지 않습니다."),

    //인증 관련
    NOT_FOUND_END_POINT(HttpStatus.NOT_FOUND, "40400", "존재하지 않는 API 엔드포인트입니다."),
    NOT_FOUND_LOGIN_USER( HttpStatus.NOT_FOUND,"40401", "로그인한 사용자가 존재하지 않습니다."),
    INVALID_HEADER_ERROR(HttpStatus.BAD_REQUEST, "40003" ,"유효하지 않은 헤더입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN,"40300", "접근 권한이 없습니다."),
    FAILURE_LOGIN( HttpStatus.UNAUTHORIZED,"40100", "잘못된 아이디 또는 비밀번호입니다."),
    EXPIRED_TOKEN_ERROR( HttpStatus.UNAUTHORIZED,"40101", "만료된 토큰입니다."),
    INVALID_TOKEN_ERROR( HttpStatus.UNAUTHORIZED,"40102", "유효하지 않은 토큰입니다."),
    TOKEN_MALFORMED_ERROR( HttpStatus.UNAUTHORIZED,"40103", "토큰이 올바르지 않습니다."),
    TOKEN_TYPE_ERROR( HttpStatus.UNAUTHORIZED,"40104", "토큰 타입이 일치하지 않거나 비어있습니다."),
    TOKEN_UNSUPPORTED_ERROR( HttpStatus.UNAUTHORIZED,"40105", "지원하지않는 토큰입니다."),
    TOKEN_GENERATION_ERROR( HttpStatus.UNAUTHORIZED,"40106", "토큰 생성에 실패하였습니다."),
    TOKEN_UNKNOWN_ERROR( HttpStatus.UNAUTHORIZED,"40107", "알 수 없는 토큰입니다."),
    TOKEN_NULL_ERROR( HttpStatus.UNAUTHORIZED,"40108", "토큰이 존재하지 않습니다."),

    //매칭 관련
    CHALLENGER_NOT_MATCHED(HttpStatus.BAD_REQUEST, "MATCH400", "매칭된 상대가 아닙니다."),

    //rating 관련
    RATING_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "RATE400", "이미 별점을 준 상대입니다."),

    CHALLENGER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "APPLY400", "이미 지원한 공모전입니다."),

    PROFILE_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND,"IMAGE404","프로필 이미지가 존재하지 않습니다."),
    PROFILE_IMAGE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST,"IMAGE400","프로필 이미지 업로드에 실패하였습니다."),
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


//    두개가 필요한 이유가 뭘까?

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}