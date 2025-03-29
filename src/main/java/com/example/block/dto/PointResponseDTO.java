package com.example.block.dto;

import com.example.block.domain.enums.PointType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class PointResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetMyPointDTO{
        //내 잔여 포인트 조회 응답 정보
        private Long point;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetMyPointDetailDTO{
        //내 포인트 상세 내역 조회 응답 정보(개별)
        Long amount;
        PointType type;
        String reason;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetMyPointDetailListDTO{
        //내 포인트 상세 내역 조회 응답 정보(리스트)
        List<GetMyPointDetailDTO> detailList;
        Long totalElements;
    }

}
