package com.northeastern.edu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private boolean watched;
    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date updated;

    @ManyToOne
    @JsonIgnore
    private Resident resident;

    @ManyToMany(mappedBy="watchlists")
    private Set<Movie> movies;

    public Watchlist() {
    }

    public Watchlist(String name, Date created, Date updated, boolean watched) {

        this.name = name;
        this.created = created;
        this.updated = updated;
        this.watched = watched;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
        resident.getWatchlists().add(this);
    }

    public void addMovieToWatchList(Movie movie) {
        this.movies.add(movie);
        movie.getWatchlists().add(this);
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}
