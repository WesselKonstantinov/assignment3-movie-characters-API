package com.assignment.moviecharacters.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "actor")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false, length = 60)
    public String fullName;

    @Column(length = 60)
    public String alias;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Gender gender;

    @Column
    public String picture;

    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY)
    public List<Movie> movies;

    @JsonGetter("movies")
    public List<String> getMovies() {
        if (movies != null) {
            return movies.stream()
                    .map(movie -> movie.title).collect(Collectors.toList());
        }
        return null;
    }
}
