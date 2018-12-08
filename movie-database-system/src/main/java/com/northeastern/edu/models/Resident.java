package com.northeastern.edu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Resident extends User{


    @OneToMany(mappedBy = "resident", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Watchlist> watchlists = new HashSet<>();


    public Resident() {

    }

    public Resident(String username, String password, String firstName, String lastName, String email, Date dob) {
        super(username, password, firstName, lastName, email, dob);
    }


    public Resident(String username, String password, String firstName, String lastName, String email, Date dob,
                    Set<Watchlist> watchlists) {
        super(username, password, firstName, lastName, email, dob);
        this.watchlists = watchlists;
    }

    public Set<Watchlist> getWatchlists() {
        return watchlists;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlists.add(watchlist);
        if(watchlist.getResident() != this) {
            watchlist.setResident(this);
        }
    }
}
