package com.northeastern.edu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestObject, Integer> {
}
