package com.northeastern.edu.repositories;

import com.northeastern.edu.models.ResidentReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentReviewRepository extends CrudRepository<ResidentReview, Integer> {
}