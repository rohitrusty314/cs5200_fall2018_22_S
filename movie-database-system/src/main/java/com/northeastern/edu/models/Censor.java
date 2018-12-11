package com.northeastern.edu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Censor extends User{


    @OneToMany(mappedBy = "censor", fetch = FetchType.EAGER)
    private Set<Review> censoredReviews;

    public Censor() { }

    public Censor(String username, String password, String firstName, String lastName, String email, Date dob) {
        super(username, password, firstName, lastName, email, dob);
    }

    public Censor(String username, String password, String firstName, String lastName, String email, Date dob,
                   Set<Review> censoredReviews) {
        super(username, password, firstName, lastName, email, dob);
        this.censoredReviews = censoredReviews;
    }


    public Set<Review> getCensoredReviews() {
        return censoredReviews;
    }

    public void setCensoredReview(Review review) {
        this.getCensoredReviews().add(review);
        if (review.getCensor() != this) {
            review.setCensor(this);
        }
    }
}
