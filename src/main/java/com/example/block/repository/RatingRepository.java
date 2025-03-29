package com.example.block.repository;

import com.example.block.domain.User;
import com.example.block.domain.mapping.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Boolean existsByRaterIdAndRatedIdAndContestId(Integer raterId, Integer ratedId, Integer contestId);
    List<Rating> findByRated(User rated);
}
