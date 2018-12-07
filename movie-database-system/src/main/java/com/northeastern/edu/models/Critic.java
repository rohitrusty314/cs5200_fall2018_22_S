package com.northeastern.edu.models;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Critic extends User{

    @OneToMany(mappedBy = "critic", fetch = FetchType.EAGER)
    private Set<CriticRating> criticRatings = new HashSet<>();

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
