package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Movie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    Movie findMovieByImdbId(String imdbId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM movie_watchlist WHERE movie_id = :movieId", nativeQuery = true)
    void deleteMovieFromWatchlist(@Param("movieId") int movieId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM review WHERE movie_id = :movieId", nativeQuery = true)
    void deleteMovieReviews(@Param("movieId") int movieId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM critic_rating WHERE movie_id = :movieId", nativeQuery = true)
    void deleteMovieRatings(@Param("movieId") int movieId);
}
