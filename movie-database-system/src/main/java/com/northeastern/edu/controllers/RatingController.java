package com.northeastern.edu.controllers;

import com.northeastern.edu.models.Critic;
import com.northeastern.edu.models.CriticRating;
import com.northeastern.edu.models.Movie;
import com.northeastern.edu.repositories.CriticRatingRepository;
import com.northeastern.edu.repositories.CriticRepository;
import com.northeastern.edu.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RatingController {

    @Autowired
    CriticRatingRepository criticRatingRepository;

    @Autowired
    CriticRepository criticRepository;

    @Autowired
    MovieRepository movieRepository;

    @PostMapping("/api/user/{userId}/movie/{movieId}/rating")
    public CriticRating createCriticRatingForMovie(@RequestBody CriticRating criticRating,
                                                   @PathVariable("userId") int userId,
                                                   @PathVariable("movieId") int movieId) {
        Date today = java.util.Calendar.getInstance().getTime();
        CriticRating oldRating = criticRatingRepository.findCriticRatingByCriticIdAndMovieId(userId, movieId);
        if(oldRating != null) {
            oldRating.setRating(criticRating.getRating());
            oldRating.setLastUpdated(today);
            criticRating = oldRating;
        } else {
            Critic critic = criticRepository.findById(userId).get();
            criticRating.setCritic(critic);

            Movie movie = movieRepository.findById(movieId).get();
            criticRating.setMovie(movie);


            criticRating.setCreated(today);
            criticRating.setLastUpdated(today);

        }
        return criticRatingRepository.save(criticRating);
    }
}
