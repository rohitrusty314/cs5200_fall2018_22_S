package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
