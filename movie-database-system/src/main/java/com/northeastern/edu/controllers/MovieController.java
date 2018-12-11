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
        Movie m = movieRepository.findMovieByImdbId(movie.getImdbId());
        if(m == null) {
            return movieRepository.save(movie);
        } else {
            return m;
        }

    }

    @DeleteMapping("/api/movie/{movieId}")
    public void deleteMovie (@PathVariable("movieId") int movieId) {
        movieRepository.deleteMovieFromWatchlist(movieId);
        movieRepository.deleteMovieRatings(movieId);
        movieRepository.deleteMovieReviews(movieId);
        movieRepository.deleteById(movieId);
    }

    @PutMapping("/api/movie/{movieId}")
    public Movie updateMovie (@PathVariable("movieId") int movieId,
                              @RequestBody Movie movie) {

        Movie m = movieRepository.findById(movieId).get();
        m.setName(movie.getName());
        m.setLanguage(movie.getLanguage());
        m.setGenre(movie.getGenre());
        m.setImdbId(movie.getImdbId());
        m.setReleaseDate(movie.getReleaseDate());

        return movieRepository.save(m);
    }

    @GetMapping("/api/movies")
    public List<Movie> getMovies() {
        return (List<Movie>) movieRepository.findAll();
    }



    @GetMapping("/api/movie/{movieId}")
    public Movie getMovie(@PathVariable("movieId") int movieId) {
        return movieRepository.findById(movieId).get();
    }

    @GetMapping("/api/movie/{movieId}/reviews/critic")
    public List<CriticReview> getAllCriticMovieReviews(@PathVariable("movieId") String movieId) {
        Movie movie = movieRepository.findMovieByImdbId(movieId);
        List<CriticReview> criticReviews = new ArrayList<>();

        for(Review review : movie.getReviews()){
            if(review instanceof CriticReview) {
                criticReviews.add((CriticReview) review);
            }
        }
        return criticReviews;
    }

    @GetMapping("/api/movie/{movieId}/reviews/resident")
    public List<ResidentReview> getAllResidentMovieReviews(@PathVariable("movieId") String movieId) {
        Movie movie = movieRepository.findMovieByImdbId(movieId);
        List<ResidentReview> residentReviews = new ArrayList<>();

        for(Review review : movie.getReviews()){
            if(review instanceof ResidentReview) {
                residentReviews.add((ResidentReview) review);
            }
        }
        return residentReviews;
    }

    @GetMapping("/api/movie/{movieId}/ratings/critic")
    public double getAverageCriticRatingsForMovie(@PathVariable("movieId") String movieId) {
        Set<CriticRating> criticRatings = movieRepository.findMovieByImdbId(movieId).getCriticRatings();
        double sum = 0;
        for(CriticRating criticRating : criticRatings) {
            sum += criticRating.getRating();
        }
        if(criticRatings.size() > 0) {
            return sum/criticRatings.size();
        }

        return  0;
    }

    @GetMapping("/api/movie/{movieId}/cast")
    public Set<Cast> getMovieCast(@PathVariable("movieId") int movieId) {
        return movieRepository.findById(movieId).get().getCast();
    }
}