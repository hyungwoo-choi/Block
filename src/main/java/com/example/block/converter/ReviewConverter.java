package com.example.block.converter;


import com.example.block.domain.Contest;
import com.example.block.domain.User;
import com.example.block.domain.mapping.Review;
import com.example.block.domain.mapping.ReviewAverageScore;
import com.example.block.dto.ReviewRequestDTO;
import com.example.block.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

//    dto - > entity
    public static Review toReview(ReviewRequestDTO.ReviewDTO request, User user, Contest contest){
        return Review.builder()
                .contest(contest)
                .user(user)
                .content(request.getContent())
                .service(request.getService())
                .prize(request.getPrize())
                .build();
    }
    public static ReviewResponseDTO.ReviewResultDTO toAddReviewResultDTO(Review review){
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .message("add!")
                .build();
    }
    public static ReviewResponseDTO.ReviewResultDTO toUpdateReviewResultDTO(Review review){
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .message("update!")
                .build();
    }
    public static ReviewResponseDTO.ViewReviewResultDTO toViewReviewResultDTO(Review review) {
        double averageScore = review.getReviewAverageScoresList().stream().mapToDouble(ReviewAverageScore::getScore).average().orElse(0.0);
        return ReviewResponseDTO.ViewReviewResultDTO.builder()
                .reviewId(review.getId())
                .userName(review.getUser().getName())
                .prize(review.getPrize())
                .score(averageScore)
                .build();
    }
    public static ReviewResponseDTO.ViewReviewResultListDTO toViewReviewResultListDTO(List<Review> reviews) {
        List<ReviewResponseDTO.ViewReviewResultDTO> viewReviewResultDTOList = reviews.stream()
                .map(ReviewConverter::toViewReviewResultDTO).collect(Collectors.toList());
        return ReviewResponseDTO.ViewReviewResultListDTO.builder()
                .reviewList(viewReviewResultDTOList)
                .message("view!")
                .build();
    }

    public static ReviewResponseDTO.GetReviewDetailDTO toReviewDetailDTO(Review review) {
        //리뷰 상세 내용 정보 변환
        double averageScore = review.getReviewAverageScoresList().stream().mapToDouble(ReviewAverageScore::getScore).average().orElse(0.0);

        return ReviewResponseDTO.GetReviewDetailDTO.builder()
                .writer(review.getUser().getName())
                .content(review.getContent())
                .service(review.getService())
                .prize(review.getPrize())
                .createdAt(review.getCreated_at())
                .score(averageScore)
                .build();
    }

    public static ReviewAverageScore toReviewAverageScore(ReviewRequestDTO.RateReviewDTO request, User user, Review review){
        return ReviewAverageScore.builder()
                .user(user)
                .review(review)
                .score(request.getScore())
                .build();
    }
}
