package com.assignment.moviecharacters.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    public String name;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    public String description;

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    public List<Movie> movies;

    @JsonGetter("movies")
    public List<String> getMovies() {
        if (movies != null) {
            return movies.stream()
                    .map(movie -> "/api/movies/" + movie.id).collect(Collectors.toList());
        }
        return null;
    }
}
