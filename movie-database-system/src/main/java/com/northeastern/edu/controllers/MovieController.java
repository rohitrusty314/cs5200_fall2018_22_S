package com.northeastern.edu.controllers;

import com.northeastern.edu.models.*;
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

    @GetMapping("/api/movie/{movieId}/reviews/resident")
    public List<ResidentReview> getAllResidentMovieReviews(@PathVariable("movieId") int movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        List<ResidentReview> residentReviews = new ArrayList<>();

        for(Review review : movie.getReviews()){
            if(review instanceof ResidentReview) {
                residentReviews.add((ResidentReview) review);
            }
        }
        return residentReviews;
    }

    @GetMapping("/api/movie/{movieId}/ratings/critic")
    public double getAverageCriticRatingsForMovie(@PathVariable("movieId") int movieId) {
        Set<CriticRating> criticRatings = movieRepository.findById(movieId).get().getCriticRatings();
        double sum = 0;
        for(CriticRating criticRating : criticRatings) {
            sum += criticRating.getRating();
        }

        return sum/criticRatings.size();
    }

    @GetMapping("/api/movie/{movieId}/cast")
    public Set<Cast> getMovieCast(@PathVariable("movieId") int movieId) {
        return movieRepository.findById(movieId).get().getCast();
    }
}