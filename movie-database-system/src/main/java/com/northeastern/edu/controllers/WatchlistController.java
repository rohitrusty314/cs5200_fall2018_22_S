package com.northeastern.edu.controllers;

import com.northeastern.edu.models.*;
import com.northeastern.edu.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class WatchlistController {

    @Autowired
    WatchlistRepository watchlistRepository;

    @Autowired
    MovieWatchlistRepository movieWatchlistRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ResidentRepository residentRepository;


    @PostMapping("/api/user/{userid}/watchlist")
    public Watchlist createWatchlist (@PathVariable("userid") int uid, @RequestBody Watchlist watchlist) {
        watchlist.setResident(residentRepository.findById(uid).get());
        return watchlistRepository.save(watchlist);
    }

    @GetMapping("/api/watchlist/{id}/movies")
    public Set<Movie> fetchWatchlistMovies(@PathVariable("id") int wId) {
        List<MovieWatchlist> movieWatchlists = movieWatchlistRepository.findByWatchlistId(wId);
        return movieWatchlists.stream().map(MovieWatchlist::getMovie).collect(Collectors.toSet());
    }


    @GetMapping("/api/watchlist/{wid}/movie/{mid}")
    public MovieWatchlist addMovieToWatchlist(@PathVariable("wid") int wId, @PathVariable("mid") int mid) {

        Movie fetchedMovie = movieRepository.findById(mid).get();
        Watchlist watchlist = watchlistRepository.findById(wId).get();
        Set<MovieWatchlist> movieWatchlists = watchlist.getMovieWatchlists();

        MovieWatchlist movieWatchlist = new MovieWatchlist(false);
        movieWatchlist.setWatchlist(watchlist);
        movieWatchlist.setMovie(fetchedMovie);
        movieWatchlists.add(movieWatchlist);
        return  movieWatchlistRepository.save(movieWatchlist);

    }

    @GetMapping("/api/watchlist/{wid}/movie/{mid}/watch")
    public Watchlist setMovieToWatched(@PathVariable("wid") int wid, @PathVariable("mid") int mid,
                                       @RequestParam("watched") boolean watched) {
        MovieWatchlist movieWatchlist = movieWatchlistRepository.findByWatchlistAndMovieId(wid, mid);
        movieWatchlist.setWatched(watched);
        return movieWatchlistRepository.save(movieWatchlist).getWatchlist();
    }



}
