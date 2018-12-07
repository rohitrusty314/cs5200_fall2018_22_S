package com.northeastern.edu.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String gender;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @ManyToMany
    @JoinTable(name="cast_movie",
            joinColumns=@JoinColumn(name="cast_id",
                    referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name=
                    "movie_id", referencedColumnName="id"))
    private Set<Movie> movies;

    public Cast() {

    }

    public Cast(String firstName, String lastName, String gender, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void linkMovieToCast(Movie movie) {
        this.movies.add(movie);
        movie.getCast().add(this);
    }
}
