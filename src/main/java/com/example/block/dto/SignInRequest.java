package com.example.block.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    @NotBlank
    @Schema(description = "이메일", example = "example.google.com")
    private String email;

    @NotBlank
    @Schema(description = "비밀번호", example = "1234")
    private String password;

}
