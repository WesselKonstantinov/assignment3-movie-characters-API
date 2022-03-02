package com.assignment.moviecharacters.Repositories;

import com.assignment.moviecharacters.Models.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository
        extends JpaRepository<MovieCharacter, Long> {
}
