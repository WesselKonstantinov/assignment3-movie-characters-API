package com.assignment.moviecharacters.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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
    public List<Movie> movies = new ArrayList<>();
}
