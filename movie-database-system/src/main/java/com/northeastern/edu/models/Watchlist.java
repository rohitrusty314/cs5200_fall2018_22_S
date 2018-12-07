package com.northeastern.edu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date updated;

    @ManyToOne
    @JsonIgnore
    private Resident resident;

    @OneToMany(mappedBy = "watchlist", fetch = FetchType.EAGER)
    private Set<MovieWatchlist> movieWatchlists = new HashSet<>();

    public Watchlist() {
    }

    public Watchlist(String name, Date created, Date updated, boolean watched) {

        this.name = name;
        this.created = created;
        this.updated = updated;

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

    public Set<MovieWatchlist> getMovieWatchlists() {
        return movieWatchlists;
    }

    public void setMovieWatchlists(Set<MovieWatchlist> movieWatchlists) {
        this.movieWatchlists = movieWatchlists;
    }

    public void linkWatchListToMovie(MovieWatchlist movieWatchlist) {
        this.movieWatchlists.add(movieWatchlist);
        if (movieWatchlist.getWatchlist() != this) {
            movieWatchlist.setWatchlist(this);
        }
    }
}
