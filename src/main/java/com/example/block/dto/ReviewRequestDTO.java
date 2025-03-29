package com.example.block.dto;

import com.example.block.domain.Contest;
import com.example.block.domain.User;
import com.example.block.domain.mapping.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


public class ReviewRequestDTO {
    @Getter
    @Builder
    public static class ReviewDTO {

        @NotNull
        String content;

        @NotNull
        String service;

        @NotNull
        String prize;
    }
    @Getter
    public static class RateReviewDTO{
        @NotNull
        double score;
    }
}
