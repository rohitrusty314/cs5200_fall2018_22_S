package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    Movie findMovieByImdbId(String imdbId);
}
