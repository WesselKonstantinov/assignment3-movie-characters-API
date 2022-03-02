package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.MovieCharacter;
import com.assignment.moviecharacters.Repositories.MovieCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/characters")
public class MovieCharacterController {

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    //Get all characters
    @GetMapping("/all")
    public ResponseEntity<List<MovieCharacter>> getAllMovieCharacters() {
        HttpStatus status = HttpStatus.OK;
        List<MovieCharacter> movieCharacters = movieCharacterRepository.findAll();
        return new ResponseEntity<>(movieCharacters, status);
    }

    // Get specific character
    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacter> getMovieCharacter(@PathVariable Long id) {
        MovieCharacter movieCharacter = new MovieCharacter();
        HttpStatus status;

        if (movieCharacterRepository.existsById(id)) {
            status = HttpStatus.OK;
            movieCharacter = movieCharacterRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movieCharacter, status);
    }

    //Add character
    @PostMapping("/add")
    public ResponseEntity<MovieCharacter> addMovieCharacter(@RequestBody MovieCharacter movieCharacter) {
        HttpStatus status;

        if (movieCharacter.id != null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(movieCharacter, status);
        }
        status = HttpStatus.OK;
        movieCharacter = movieCharacterRepository.save(movieCharacter);
        return new ResponseEntity<>(movieCharacter, status);
    }

    //Update character
    @PatchMapping("/{id}")
    public ResponseEntity<MovieCharacter> updateMovieCharacter(@PathVariable Long id, @RequestBody MovieCharacter newMovieCharacter) {
        MovieCharacter movieCharacter = new MovieCharacter();
        HttpStatus status;

        if (movieCharacterRepository.existsById(id)) {

            Optional<MovieCharacter> characterRepos = movieCharacterRepository.findById(id);
            movieCharacter = characterRepos.get();

            if (newMovieCharacter.fullName != null) {
                movieCharacter.fullName = newMovieCharacter.fullName;
            }
            if (newMovieCharacter.alias != null) {
                movieCharacter.alias = newMovieCharacter.alias;
            }
            if (newMovieCharacter.gender != null) {
                movieCharacter.gender = newMovieCharacter.gender;
            }
            if (newMovieCharacter.picture != null) {
                movieCharacter.picture = newMovieCharacter.picture;
            }
            if (newMovieCharacter.movies != null && !newMovieCharacter.movies.isEmpty()) {
                movieCharacter.movies.addAll(newMovieCharacter.movies);
            }
            status = HttpStatus.OK;
            movieCharacterRepository.save(movieCharacter);

        } else {
            status = HttpStatus.NOT_FOUND;

        }
        return new ResponseEntity<>(movieCharacter, status);
    }

    //Delete Character
    @DeleteMapping("/{id}")
    public ResponseEntity<MovieCharacter> deleteMovieCharacter(@PathVariable Long id) {
        HttpStatus status;

        if (movieCharacterRepository.existsById(id)) {
            movieCharacterRepository.deleteById(id);
            status = HttpStatus.OK;

        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(status);

    }
}
