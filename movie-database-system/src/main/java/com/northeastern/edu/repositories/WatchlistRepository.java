package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Watchlist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM watchlist WHERE resident_id = :residentId", nativeQuery = true)
    void deleteByResidentId(@Param("residentId") int residentId);

    @Query(value = "select wl.* from watchlist wl join watchlist_endorsement wle " +
            "on wl.id = wle.watchlist_id group by wle.watchlist_id order by count(*) desc limit :limit", nativeQuery = true)
    List<Watchlist> findWatchlistsWithMostEndorsements(@Param("limit") int limit);

}
