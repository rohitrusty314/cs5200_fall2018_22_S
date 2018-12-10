package com.northeastern.edu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Critic extends User{


    @OneToMany(mappedBy = "critic", fetch = FetchType.EAGER)
    private Set<CriticRating> criticRatings = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="Follow",
    joinColumns=@JoinColumn(name="critic_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="resident_id", referencedColumnName = "id"))
    private List<Resident> followedByResidents;

    public Critic() { }

    public Critic(String username, String password, String firstName, String lastName, String email, Date dob) {
        super(username, password, firstName, lastName, email, dob);
    }

    public void setCriticRatings(Set<CriticRating> criticRatings) {
        this.criticRatings = criticRatings;
    }

    public List<Resident> getFollowedByResidents() {
        return followedByResidents;
    }

    public void setFollowedByResidents(List<Resident> followedByResidents) {
        this.followedByResidents = followedByResidents;
    }

    public Critic(String username, String password, String firstName, String lastName, String email, Date dob,
                  Set<CriticRating> criticRatings) {
        super(username, password, firstName, lastName, email, dob);
        this.criticRatings = criticRatings;
    }

    public Set<CriticRating> getCriticRatings() {
        return criticRatings;
    }

    public void setCriticRating(CriticRating criticRating) {
        this.criticRatings.add(criticRating);
        if(criticRating.getCritic() != this) {
            criticRating.setCritic(this);
        }
    }
}
