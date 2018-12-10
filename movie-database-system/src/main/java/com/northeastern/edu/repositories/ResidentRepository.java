package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Resident;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ResidentRepository extends CrudRepository<Resident, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM follow WHERE resident_id = :residentId", nativeQuery = true)
    void deleteFollowingByResidentId(@Param("residentId") int residentId);
}
