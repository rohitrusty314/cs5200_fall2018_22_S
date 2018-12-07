package com.northeastern.edu.controllers;

import com.northeastern.edu.models.Movie;
import com.northeastern.edu.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
