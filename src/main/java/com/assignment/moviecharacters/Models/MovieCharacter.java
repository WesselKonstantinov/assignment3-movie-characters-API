package com.assignment.moviecharacters.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "actor")
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    public String fullName;

    @Size(max = 60)
    @Column(length = 60)
    public String alias;

    @NotBlank
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Gender gender;

    @Column
    public String picture;

    @ManyToMany(mappedBy = "movieCharacters", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
