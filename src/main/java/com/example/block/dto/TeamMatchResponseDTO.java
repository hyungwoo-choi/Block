package com.example.block.dto;

import com.example.block.domain.enums.ApplyPart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TeamMatchResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengerListResultDTO{
        Integer id;    // 지원자 ID
        String name;
        String university;
        ApplyPart applyPart;
        String profileImageUrl;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengerResultDTO{
        String name;
        String university;
        String major;
        String portfolio;
        ApplyPart applyPart;
        double score;   // 별점
        String content; // 자기소개
        Boolean liked;  // 하트
        String profileImageUrl;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberResultDTO{
        String name;
        String university;
    }
}
