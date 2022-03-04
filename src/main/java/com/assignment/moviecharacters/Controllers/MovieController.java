package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Movie;
import com.assignment.moviecharacters.Services.MovieService;
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
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Operation(summary = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all movies",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))})
    })
    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @Operation(summary = "Get a movie by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the movie",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "404", description = "Movie not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@Parameter(description = "id of movie to be searched") @PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @Operation(summary = "Get all movie characters in a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all movie characters in the selected movie",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "404", description = "Movie and related movie characters not found",
                    content = @Content)
    })
    @GetMapping("/{id}/characters")
    public ResponseEntity<List<String>> getAllMovieCharactersInMovie(@Parameter(description = "id of movie to be searched") @PathVariable Long id) {
        return movieService.getAllMovieCharactersInMovie(id);
    }

    @Operation(summary = "Add a new movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added new movie",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))})
    })
    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @Operation(summary = "Update an existing movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the selected movie",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "404", description = "Movie not found",
                    content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@Parameter(description = "id of movie to be updated") @PathVariable Long id, @RequestBody Movie newMovie) {
        return movieService.updateMovie(id, newMovie);
    }

    @Operation(summary = "Update characters in a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated movie characters in the selected movie",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "404", description = "Movie not found",
                    content = @Content)
    })
    @PatchMapping("/{id}/characters/update")
    public ResponseEntity<Movie> updateCharactersInMovie(@Parameter(description = "id of movie to be updated") @PathVariable Long id, @RequestBody Long[] characterIds) {
        return movieService.updateCharactersInMovie(id, characterIds);
    }

    @Operation(summary = "Delete a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the selected movie",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@Parameter(description = "id of movie to be deleted") @PathVariable Long id) {
       return movieService.deleteMovie(id);
    }
}
