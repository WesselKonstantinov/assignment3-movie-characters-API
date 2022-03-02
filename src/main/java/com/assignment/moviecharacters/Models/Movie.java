package com.assignment.moviecharacters.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 160)
    public String title;

    @Column(nullable = false, length = 60)
    public String genre;

    @Column(nullable = false)
    public Integer releaseYear;

    @Column(nullable = false, length = 60)
    public String director;

    @Column
    public String picture;

    @Column
    public String trailer;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "franchise_id")
    public Franchise franchise;

    @ManyToMany(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "movie_actors",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    public List<MovieCharacter> movieCharacters = new ArrayList<>();

    @JsonGetter("franchise")
    public String getFranchise() {
        if (franchise != null) {
            return "/api/franchises/" + franchise.id;
        }
        return null;
    }

    @JsonGetter("movieCharacters")
    public List<String> getCharacters() {
        if (movieCharacters != null) {
            return movieCharacters.stream()
                    .map(movieCharacter -> {
                        return "/api/characters/" + movieCharacter.id;
                    }).collect(Collectors.toList());
        }
        return null;
    }

}
