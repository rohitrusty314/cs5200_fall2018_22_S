package com.northeastern.edu.repositories;

import com.northeastern.edu.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
