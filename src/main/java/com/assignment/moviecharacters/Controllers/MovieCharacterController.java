package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.MovieCharacter;
import com.assignment.moviecharacters.Services.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class MovieCharacterController {

    @Autowired
    private MovieCharacterService movieCharacterService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieCharacter>> getAllMovieCharacters() {
        return movieCharacterService.getAllMovieCharacters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacter> getMovieCharacter(@PathVariable Long id) {
        return movieCharacterService.getMovieCharacter(id);
    }


    @PostMapping("/add")
    public ResponseEntity<MovieCharacter> addMovieCharacter(@RequestBody MovieCharacter movieCharacter) {
        return movieCharacterService.addMovieCharacter(movieCharacter);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieCharacter> updateMovieCharacter(@PathVariable Long id, @RequestBody MovieCharacter newMovieCharacter) {
        return movieCharacterService.updateMovieCharacter(id, newMovieCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieCharacter> deleteMovieCharacter(@PathVariable Long id) {
        return movieCharacterService.deleteMovieCharacter(id);
    }
}
