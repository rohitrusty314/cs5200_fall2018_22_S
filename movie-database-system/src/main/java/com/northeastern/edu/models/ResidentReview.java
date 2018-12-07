package com.northeastern.edu.models;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class ResidentReview extends Review{


    private boolean endorsedByCritic;

    public ResidentReview () {

    }
    public ResidentReview(String review, Date created, Date updated, boolean endorsedByCritic) {
        super(review, created, updated);
        this.endorsedByCritic = endorsedByCritic;
    }

    public boolean isEndorsedByCritic() {
        return endorsedByCritic;
    }

    public void setEndorsedByCritic(boolean endorsedByCritic) {
        this.endorsedByCritic = endorsedByCritic;
    }
}
