package com.example.block.repository;

import com.example.block.domain.mapping.ReviewAverageScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewAverageScoreRepository extends JpaRepository<ReviewAverageScore, Integer> {
    Optional<ReviewAverageScore> findByUserIdAndReviewId(Integer userId, Integer reviewId);

}
