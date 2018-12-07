package com.northeastern.edu.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    private String genre;

    private String language;

    @ManyToMany
    @JoinTable(name="movie_watchlist",
            joinColumns=@JoinColumn(name="movie_id",
                    referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name=
                    "watchlist_id", referencedColumnName="id"))
    private Set<Watchlist> watchlists;


    public Movie () {

    }


    public Movie (String name, Date releaseDate, String genre, String language) {

        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.language = language;

    }

    public Set<Watchlist> getWatchlists() {
        return watchlists;
    }


    public void linkWatchlistToMovie(Watchlist watchlist) {
        this.watchlists.add(watchlist);
        if(!watchlist.getMovies().contains(this)) {
            watchlist.getMovies().add(this);
        }
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
