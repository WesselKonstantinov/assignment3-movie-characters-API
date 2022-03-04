package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.MovieCharacter;
import com.assignment.moviecharacters.Services.MovieCharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class MovieCharacterController {

    @Autowired
    private MovieCharacterService movieCharacterService;

    @Operation(summary = "Get all movie characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all movie characters",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieCharacter.class))})
    })
    @GetMapping("/all")
    public ResponseEntity<List<MovieCharacter>> getAllMovieCharacters() {
        return movieCharacterService.getAllMovieCharacters();
    }

    @Operation(summary = "Get a movie character by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the movie character",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieCharacter.class))}),
            @ApiResponse(responseCode = "404", description = "Movie character not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacter> getMovieCharacter(@Parameter(description = "id of movie character to be searched") @PathVariable Long id) {
        return movieCharacterService.getMovieCharacter(id);
    }

    @Operation(summary = "Add a new movie character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added new movie character",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieCharacter.class))})
    })
    @PostMapping("/add")
    public ResponseEntity<MovieCharacter> addMovieCharacter(@RequestBody MovieCharacter movieCharacter) {
        return movieCharacterService.addMovieCharacter(movieCharacter);
    }

    @Operation(summary = "Update an existing movie character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the selected movie character",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieCharacter.class))}),
            @ApiResponse(responseCode = "404", description = "Movie character not found",
                    content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<MovieCharacter> updateMovieCharacter(@Parameter(description = "id of movie character to be updated") @PathVariable Long id, @RequestBody MovieCharacter newMovieCharacter) {
        return movieCharacterService.updateMovieCharacter(id, newMovieCharacter);
    }

    @Operation(summary = "Delete a movie character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the selected movie character",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie character not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MovieCharacter> deleteMovieCharacter(@Parameter(description = "id of movie character to be deleted") @PathVariable Long id) {
        return movieCharacterService.deleteMovieCharacter(id);
    }
}
