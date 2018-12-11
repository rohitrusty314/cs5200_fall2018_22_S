package com.northeastern.edu.repositories;

import com.northeastern.edu.models.CriticReview;
import com.northeastern.edu.models.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
    @Query(value="SELECT * FROM review WHERE movie_id = :movieId and user_id = :userId", nativeQuery = true)
    Review findByMovieIdAndUserId(@Param("movieId") int movieId, @Param("userId") int userId);

<<<<<<< HEAD

    @Query(value = "SELECT * from review WHERE user_id= :userId", nativeQuery = true )
    List<Review> findAllByUserId(@Param("userId") int userId);

=======
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM review WHERE user_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") int userId);
>>>>>>> 44faa10a49e9757b61f2076f22bdf21131efe257
}
