package com.northeastern.edu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Curator extends User{



    @JsonIgnore
    @ManyToMany
    @JoinTable(name="watchlist_endorsement",
            joinColumns=@JoinColumn(name="curator_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="watchlist_id", referencedColumnName = "id"))
    private Set<Watchlist> enorsedWatchList;

    public Curator() { }

    public Curator(String username, String password, String firstName, String lastName, String email, Date dob) {
        super(username, password, firstName, lastName, email, dob);
    }

    public Curator(String username, String password, String firstName, String lastName, String email, Date dob,
                   Set<Watchlist> enorsedWatchList) {
        super(username, password, firstName, lastName, email, dob);
        this.enorsedWatchList = enorsedWatchList;
    }

    public Set<Watchlist> getEnorsedWatchList() {
        return enorsedWatchList;
    }

    public void setEnorsedWatchList(Set<Watchlist> enorsedWatchList) {
        this.enorsedWatchList = enorsedWatchList;
    }

    public void endorseWatchlist(Watchlist watchlist) {
        this.enorsedWatchList.add(watchlist);
        if(!watchlist.getEndorsements().contains(this)) {
            watchlist.getEndorsements().add(this);
        }
    }
}
