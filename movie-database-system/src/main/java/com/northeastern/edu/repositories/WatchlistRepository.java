package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Watchlist;
import org.springframework.data.repository.CrudRepository;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer> {
}
