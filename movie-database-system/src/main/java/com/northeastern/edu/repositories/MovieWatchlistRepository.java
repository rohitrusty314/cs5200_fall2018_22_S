package com.northeastern.edu.repositories;

import com.northeastern.edu.models.MovieWatchlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieWatchlistRepository extends CrudRepository<MovieWatchlist, Integer> {

    @Query(value = "SELECT * from movie_watchlist WHERE watchlist_id= :wid", nativeQuery = true )
    List<MovieWatchlist> findByWatchlistId(@Param("wid") int wid);

    @Query(value = "SELECT * from movie_watchlist  WHERE watchlist_id= :wid AND movie_id= :mid", nativeQuery = true)
    MovieWatchlist findByWatchlistAndMovieId(@Param("wid") int wid, @Param("mid") int mid);
}
