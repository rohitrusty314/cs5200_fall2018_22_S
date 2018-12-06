package com.northeastern.edu.models;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class CriticReview extends Review{

    private long upVotes;
    private long downVotes;

    public CriticReview(String review, Date created, Date updated, long upVotes, long downVotes) {
        super(review, created, updated);
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }


    public long getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(long upVotes) {
        this.upVotes = upVotes;
    }

    public long getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(long downVotes) {
        this.downVotes = downVotes;
    }
}
