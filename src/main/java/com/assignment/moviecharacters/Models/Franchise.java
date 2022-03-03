package com.assignment.moviecharacters.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 80)
    public String name;

    @Column(nullable = false)
    public String description;

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    public List<Movie> movies;

    @JsonGetter("movies")
    public List<String> getMovies() {
        if (movies != null) {
            return movies.stream()
                    .map(movie -> {
                        return "/api/movies/" + movie.id;
                    }).collect(Collectors.toList());
        }
        return null;
    }
}
