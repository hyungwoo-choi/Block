package com.example.block.controller;

import com.example.block.service.AuthService;
import com.example.block.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contest/{contestId}")
public class RatingController {

    private final RatingService ratingService;
    private final AuthService authService;

    @PostMapping("/challenger/{ratedUserId}/score")
    public void rateUser(@PathVariable Integer contestId,
                         @PathVariable Integer ratedUserId,
                         @RequestParam double score){

        Integer userId = authService.getUserIdFromSecurity();
        ratingService.rateUser(userId, ratedUserId, contestId, score);
    }
}
