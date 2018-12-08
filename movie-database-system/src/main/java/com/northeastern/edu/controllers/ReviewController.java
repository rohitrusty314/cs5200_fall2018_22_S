package com.northeastern.edu.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.northeastern.edu.models.Critic;
import com.northeastern.edu.models.CriticReview;
import com.northeastern.edu.models.Movie;
import com.northeastern.edu.models.Review;
import com.northeastern.edu.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CriticReviewRepository criticReviewRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CriticRepository criticRepository;

    @PostMapping("/api/user/{userId}/movie/{movieId}/review/critic")
    public CriticReview createCriticReviewForMovie(@RequestBody CriticReview criticReview,
                                                   @PathVariable("userId") int userId,
                                                   @PathVariable("movieId") int movieId) {
        Date today = java.util.Calendar.getInstance().getTime();
        Review oldReview = reviewRepository.findByMovieIdAndUserId(movieId, userId);
        if(oldReview != null) {
            oldReview.setReview(criticReview.getReview());
            oldReview.setUpdated(today);
            criticReview = (CriticReview) oldReview;
        } else {
            Critic critic = criticRepository.findById(userId).get();
            criticReview.setUser(critic);

            Movie movie = movieRepository.findById(movieId).get();
            criticReview.setMovie(movie);


            criticReview.setCreated(today);
            criticReview.setUpdated(today);
            criticReview.setUpVotes(0);
            criticReview.setDownVotes(0);
        }
        return criticReviewRepository.save(criticReview);
    }
}
