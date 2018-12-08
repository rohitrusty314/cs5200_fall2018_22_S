package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
    @Query(value="SELECT * FROM review WHERE movie_id = :movieId and user_id = :userId", nativeQuery = true)
    Review findByMovieIdAndUserId(@Param("movieId") int movieId, @Param("userId") int userId);
}
