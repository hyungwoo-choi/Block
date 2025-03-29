package com.example.block.service;

import com.example.block.domain.Contest;
import com.example.block.domain.User;
import com.example.block.domain.mapping.Rating;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.handler.RatingHandler;
import com.example.block.repository.ContestRepository;
import com.example.block.repository.MatchesRepository;
import com.example.block.repository.RatingRepository;
import com.example.block.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final UserRepository userRepository;
    private final MatchesRepository matchesRepository;
    private final RatingRepository ratingRepository;
    private final ContestRepository contestRepository;

    @Transactional
    public void rateUser(Integer raterId, Integer ratedId, Integer contestId, double score){

        User rater = userRepository.findById(raterId).get();
        User rated = userRepository.findById(ratedId).get();
        Contest contest = contestRepository.findById(contestId).get();

        Boolean isMatched = matchesRepository.existsByContestIdAndUserIds(contestId, raterId, ratedId);
        if(!isMatched){
            throw new RatingHandler(ErrorStatus.CHALLENGER_NOT_MATCHED);
        }

        Boolean hasRated = ratingRepository.existsByRaterIdAndRatedIdAndContestId(raterId, ratedId, contestId);
        if(hasRated){
            throw new RatingHandler(ErrorStatus.RATING_ALREADY_COMPLETED);
        }

        Rating rating = Rating.builder()
                .rater(rater)
                .rated(rated)
                .contest(contest)
                .score(score)
                .build();

        ratingRepository.save(rating);

        updateAverageRating(rated, score);
    }

    @Transactional
    public void updateAverageRating(User rated, double newScore){

        List<Rating> ratings = ratingRepository.findByRated(rated);

        if(ratings.size() == 1){
            rated.setScore(newScore);
        } else {
            double averageRating = ratings.stream().mapToDouble(Rating::getScore).average().orElse(newScore);
            rated.setScore(averageRating);
        }

        userRepository.save(rated);
    }

    public double getAverageRating(Integer userId){

        User rated = userRepository.findById(userId).get();
        return rated.getScore();
    }
}
