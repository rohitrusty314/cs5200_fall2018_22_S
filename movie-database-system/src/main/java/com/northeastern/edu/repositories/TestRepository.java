package com.northeastern.edu.repositories;

import com.northeastern.edu.models.TestObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestObject, Integer> {
}
