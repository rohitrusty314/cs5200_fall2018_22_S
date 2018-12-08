package com.northeastern.edu.controllers;

import com.northeastern.edu.models.*;
import com.northeastern.edu.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CriticReviewRepository criticReviewRepository;

    @Autowired
    ResidentReviewRepository residentReviewRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CriticRepository criticRepository;

    @Autowired
    ResidentRepository residentRepository;

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

    @PostMapping("/api/user/{userId}/movie/{movieId}/review/resident")
    public ResidentReview createResidentReviewForMovie(@RequestBody ResidentReview residentReview,
                                                   @PathVariable("userId") int userId,
                                                   @PathVariable("movieId") int movieId) {
        Date today = java.util.Calendar.getInstance().getTime();
        Review oldReview = reviewRepository.findByMovieIdAndUserId(movieId, userId);
        if(oldReview != null) {
            oldReview.setReview(residentReview.getReview());
            oldReview.setUpdated(today);
            residentReview = (ResidentReview) oldReview;
        } else {
            Resident resident = residentRepository.findById(userId).get();
            residentReview.setUser(resident);

            Movie movie = movieRepository.findById(movieId).get();
            residentReview.setMovie(movie);


            residentReview.setCreated(today);
            residentReview.setUpdated(today);
            residentReview.setEndorsedByCritic(false);
        }
        return residentReviewRepository.save(residentReview);
    }

    @PutMapping("/api/critic/review/{reviewId}/upvote")
    public CriticReview upvoteCriticReview(@PathVariable("reviewId") int reviewId) {
        CriticReview criticReview = criticReviewRepository.findById(reviewId).get();
        criticReview.setUpVotes(criticReview.getUpVotes() + 1);
        return criticReviewRepository.save(criticReview);
    }

    @PutMapping("/api/critic/review/{reviewId}/downvote")
    public CriticReview downvoteCriticReview(@PathVariable("reviewId") int reviewId) {
        CriticReview criticReview = criticReviewRepository.findById(reviewId).get();
        criticReview.setDownVotes(criticReview.getDownVotes() + 1);
        return criticReviewRepository.save(criticReview);
    }

    @PutMapping("/api/resident/review/{reviewId}/endorse")
    public ResidentReview endorseResidentReview(@PathVariable("reviewId") int reviewId) {
        ResidentReview residentReview = residentReviewRepository.findById(reviewId).get();
        residentReview.setEndorsedByCritic(true);
        return residentReviewRepository.save(residentReview);
    }
}
