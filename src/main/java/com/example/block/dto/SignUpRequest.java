package com.example.block.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Schema(description = "providerId", example = "203912941")
    private Long providerId;

    @NotBlank
    @Schema(description = "이메일", example = "example.google.com")
    private String email;

    @NotBlank
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @NotBlank
    @Schema(description = "플랫폼", example = "general")
    private String platform;

    @NotBlank
    @Schema(description = "이름", example = "홍길동")
    private String name;

    @NotBlank
    @Schema(description = "전화번호", example = "010-1234-1234")
    private String phoneNumber;

    @NotBlank
    @Schema(description = "대학이름", example = "umc 대학교")
    private String university;

    @NotBlank
    @Schema(description = "생년월일", example = "2001-01-01")
    private String birthDay;

    @NotBlank
    @Schema(description = "대학교전공", example = "컴퓨터학부")
    private String univMajor;

    @NotBlank
    @Schema(description = "포트폴리오", example = "example.notion.com")
    private String portfolio;




}
