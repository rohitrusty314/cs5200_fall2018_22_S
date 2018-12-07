package com.northeastern.edu.controllers;

import com.northeastern.edu.models.CriticReview;
import com.northeastern.edu.models.Movie;
import com.northeastern.edu.models.ResidentReview;
import com.northeastern.edu.models.Review;
import com.northeastern.edu.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @PostMapping("/api/movie")
    public Movie createMovie (@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("/api/movie/{movieId}")
    public Movie getMovie(@PathVariable("movieId") int movieId) {
        return movieRepository.findById(movieId).get();
    }

    @GetMapping("/api/movie/{movieId}/reviews/critic")
    public List<CriticReview> getAllCriticMovieReviews(@PathVariable("movieId") int movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        List<CriticReview> criticReviews = new ArrayList<>();

        for(Review review : movie.getReviews()){
            if(review instanceof CriticReview) {
                criticReviews.add((CriticReview) review);
            }
        }
        return criticReviews;
    }

    @GetMapping("/api/movie/{movieId}/reviews/critic")
    public List<ResidentReview> getAllResidentMovieReviews(@PathVariable("movieId") int movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        List<ResidentReview> residentReviews = new ArrayList<>();

        for(Review review : movie.getReviews()){
            if(review instanceof CriticReview) {
                residentReviews.add((ResidentReview) review);
            }
        }
        return residentReviews;
    }
}