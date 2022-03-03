package com.assignment.moviecharacters.Repositories;

import com.assignment.moviecharacters.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //check if given movie exists
    boolean movieByTitleExists(String title);

    Movie findMovieByTitle(String title);

}
