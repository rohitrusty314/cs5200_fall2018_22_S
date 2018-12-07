package com.northeastern.edu.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Resident extends User{


    @OneToMany(mappedBy = "resident", fetch = FetchType.EAGER)
    private Set<Watchlist> watchlists = new HashSet<>();

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
