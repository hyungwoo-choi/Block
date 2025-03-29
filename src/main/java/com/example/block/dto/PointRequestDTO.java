package com.example.block.dto;

import lombok.Builder;
import lombok.Getter;

public class PointRequestDTO {
    @Getter
    public static class PointCharge{
        private Long point;
        private String reason;
    }

    @Getter
    @Builder
    public static class PointUse{
        private Long point;
        private String reason;
    }
}
