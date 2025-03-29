package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.converter.ReviewConverter;
import com.example.block.domain.mapping.Review;
import com.example.block.dto.KakaoPayRequestDTO;
import com.example.block.dto.ReviewRequestDTO;
import com.example.block.dto.ReviewResponseDTO;
import com.example.block.service.AuthService;
import com.example.block.service.LikeService;
import com.example.block.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("contest/{contestId}/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final AuthService authService;

    @PostMapping("/regist")
    @Operation(summary = "리뷰 등록")
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> addReview(@Valid @RequestBody ReviewRequestDTO.ReviewDTO request, @PathVariable("contestId") Integer contestId){
        Integer userId = authService.getUserIdFromSecurity();

        Review review = reviewService.addReview(userId, contestId ,request);
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(review));
    }

    @PostMapping("/{reviewId}")
    @Operation(summary = "리뷰 수정")
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> updateReview(@Valid @PathVariable("reviewId") Integer reviewId, @RequestBody ReviewRequestDTO.ReviewDTO request){
        Integer userId = authService.getUserIdFromSecurity();
        Review review = reviewService.updateReview(userId, reviewId, request);
        return ApiResponse.onSuccess(ReviewConverter.toUpdateReviewResultDTO(review));
    }

    @PostMapping("/{reviewId}/delete")
    @Operation(summary = "리뷰삭제")
    public void deleteReview(@Valid @PathVariable ("reviewId") Integer reviewId){
        Integer userId = authService.getUserIdFromSecurity();
        reviewService.deleteReview(userId, reviewId);
    }

    @GetMapping("")
    @Operation(summary = "리뷰 리스트 출력 (간단조회)")
    public ApiResponse<ReviewResponseDTO.ViewReviewResultListDTO> viewReviewList(@Valid @PathVariable("contestId") Integer contestId){
        List<Review> reviewList = reviewService.viewReviewList(contestId);
        return ApiResponse.onSuccess(ReviewConverter.toViewReviewResultListDTO(reviewList));
    }
    @PostMapping("/{reviewId}/rate")
    @Operation(summary = "리뷰 점수 등록")
    public void rateReview(@Valid @RequestBody ReviewRequestDTO.RateReviewDTO request,
                           @PathVariable("reviewId")Integer reviewId){
        Integer userId = authService.getUserIdFromSecurity();
        reviewService.rateReview(userId, reviewId, request);
    }
}
