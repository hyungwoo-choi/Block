package com.example.block.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserLoginDto(
        @NotNull(message = "providerId는 빈값이 될 수 없습니다.")
        @JsonProperty("providerId") @Schema(description = "프로바이더 아이디", example = "203912941")
        Long providerId
) {
}
