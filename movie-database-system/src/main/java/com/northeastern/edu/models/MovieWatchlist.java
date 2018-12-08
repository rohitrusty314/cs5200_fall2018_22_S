package com.northeastern.edu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class MovieWatchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean watched;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    @JsonIgnore
    private Watchlist watchlist;

    public MovieWatchlist() {

    }

    public MovieWatchlist(boolean watched) {
        this.watched = watched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        if (!movie.getMovieWatchlists().contains(this)) {
            movie.getMovieWatchlists().add(this);
        }
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
        if (!watchlist.getMovieWatchlists().contains(this)) {
            watchlist.getMovieWatchlists().add(this);
        }
    }
}
