package com.assignment.moviecharacters.Repositories;

import com.assignment.moviecharacters.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository
        extends JpaRepository<Movie, Long> {
}
