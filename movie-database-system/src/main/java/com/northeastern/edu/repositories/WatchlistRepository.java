package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Watchlist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM watchlist WHERE resident_id = :residentId", nativeQuery = true)
    void deleteByResidentId(@Param("residentId") int residentId);
}
