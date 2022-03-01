package com.assignment.moviecharacters.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false, length = 80)
    public String name;

    @Column(nullable = false)
    public String description;

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    public List<Movie> movies = new ArrayList<>();
}
