package com.northeastern.edu.repositories;

import com.northeastern.edu.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT user from User user WHERE user.username= :username AND user.password= :password")
    List<User> findUserByCredentials(@Param("username") String username,
                                            @Param("password") String password);

    User findUserByUsername(String username);
}
