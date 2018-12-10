package com.northeastern.edu.repositories;

import com.northeastern.edu.models.CriticRating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CriticRatingRepository extends CrudRepository<CriticRating, Integer> {
    @Query(value = "SELECT * FROM critic_rating WHERE critic_id = :userId AND movie_id = :movieId", nativeQuery =  true)
    CriticRating findCriticRatingByCriticIdAndMovieId(@Param("userId") int userId, @Param("movieId") int movieId);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM critic_rating WHERE critic_id = :criticId", nativeQuery = true)
    void deleteByCriticId(@Param("criticId") int criticId);
}
