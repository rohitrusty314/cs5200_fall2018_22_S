package com.northeastern.edu.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CriticRating{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long rating;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date lastUpdated;

    @ManyToOne
    private Critic critic;

    @ManyToOne
    private Movie movie;

    public CriticRating() {
        
    }
    public CriticRating(long rating, Date created, Date lastUpdated) {

        this.rating = rating;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Critic getCritic() {
        return critic;
    }

    public void setCritic(Critic critic) {
        this.critic = critic;
        critic.getCriticRatings().add(this);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        movie.getCriticRatings().add(this);
    }
}
