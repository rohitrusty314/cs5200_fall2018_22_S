package com.northeastern.edu.controllers;

import com.northeastern.edu.models.*;
import com.northeastern.edu.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @PutMapping("/api/watchlist/{wid}/movie/{mid}/add")
    public MovieWatchlist addMovieToWatchlist(@PathVariable("wid") int wId, @PathVariable("mid") int mid) {

        Movie fetchedMovie = movieRepository.findById(mid).get();
        Watchlist watchlist = watchlistRepository.findById(wId).get();
        Set<MovieWatchlist> movieWatchlists = watchlist.getMovieWatchlists();


        boolean contains = false;
        for(MovieWatchlist mwl: movieWatchlists) {
            if(mwl.getMovie().getId() == fetchedMovie.getId()) {
                contains = true;
                break;
            }
        }

        MovieWatchlist movieWatchlist = new MovieWatchlist(false);
        if(!contains) {


            movieWatchlist.setWatchlist(watchlist);
            movieWatchlist.setMovie(fetchedMovie);
            movieWatchlists.add(movieWatchlist);
            return  movieWatchlistRepository.save(movieWatchlist);
        }


        return movieWatchlist;
    }

    @PutMapping("/api/watchlist/{wid}/movie/{mid}/delete")
    public void deleteMovieFromWatchlist(@PathVariable("wid") int watchlistId,
                                                   @PathVariable("mid") int movieId) {
        movieWatchlistRepository.deleteByWatchlistIdAndMovieId(watchlistId, movieId);
    }

    @GetMapping("/api/watchlist/{wid}/movie/{mid}/watch")
    public Watchlist setMovieToWatched(@PathVariable("wid") int wid, @PathVariable("mid") int mid,
                                       @RequestParam("watched") boolean watched) {
        MovieWatchlist movieWatchlist = movieWatchlistRepository.findByWatchlistAndMovieId(wid, mid);
        movieWatchlist.setWatched(watched);
        return movieWatchlistRepository.save(movieWatchlist).getWatchlist();
    }

    @DeleteMapping("/api/watchlist/{wid}/delete")
    public void deleteWatchlist(@PathVariable("wid") int watchlistId) {
        watchlistRepository.deleteById(watchlistId);
    }

    @GetMapping("/api/watchlists/all")
    public List<Watchlist> findAllWatchlists() {
        return (List<Watchlist>) watchlistRepository.findAll();
    }
}
