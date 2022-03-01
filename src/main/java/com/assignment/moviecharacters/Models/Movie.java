package com.assignment.moviecharacters.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

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
    @JoinColumn(name = "franchise_id")
    public Franchise franchise;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_characters",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    public List<Character> characters = new ArrayList<>();

}
