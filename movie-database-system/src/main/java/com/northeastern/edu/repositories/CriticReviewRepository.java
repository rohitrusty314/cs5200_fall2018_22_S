package com.northeastern.edu.repositories;

import com.northeastern.edu.models.CriticReview;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CriticReviewRepository extends CrudRepository<CriticReview, Integer> {
}
