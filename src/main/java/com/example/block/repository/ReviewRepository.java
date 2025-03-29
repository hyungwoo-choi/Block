package com.example.block.repository;

import com.example.block.domain.mapping.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Boolean existsByIdAndContestId(Integer reviewId, Integer contestId);
    Optional<Review> findByUserIdAndContestId(Integer userId, Integer contestId);
    List<Review> findByContestId(Integer contestId);
}
