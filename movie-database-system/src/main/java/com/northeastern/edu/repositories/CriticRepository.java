package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Critic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CriticRepository extends CrudRepository<Critic, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM follow WHERE critic_id = :criticId", nativeQuery = true)
    void deleteFollowersByCriticId(@Param("criticId") int criticId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user WHERE id = :criticId", nativeQuery = true)
    void deleteByCriticId(@Param("criticId") int criticId);

}
