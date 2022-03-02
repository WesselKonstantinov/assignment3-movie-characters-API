package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Movie;
import com.assignment.moviecharacters.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    //Get all movies
    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        HttpStatus status = HttpStatus.OK;
        List<Movie> movies = movieRepository.findAll();
        return new ResponseEntity<>(movies, status);
    }

    // Get specific movie
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            movie = movieRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movie, status);
    }

    //Add movie
    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        HttpStatus status;

        if (movie.id != null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(movie, status);
        }
        status = HttpStatus.OK;
        movie = movieRepository.save(movie);
        return new ResponseEntity<>(movie, status);
    }

    // Update movie
    @PatchMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie newMovie) {
        Movie movie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {

            Optional<Movie> movieRepos = movieRepository.findById(id);
            movie = movieRepos.get();

            if (newMovie.title != null) {
                movie.title = newMovie.title;
            }
            if (newMovie.genre != null) {
                movie.genre = newMovie.genre;
            }
            if (newMovie.releaseYear != null) {
                movie.releaseYear = newMovie.releaseYear;
            }
            if (newMovie.director != null) {
                movie.director = newMovie.director;
            }
            if (newMovie.picture != null) {
                movie.picture = newMovie.picture;
            }
            if (newMovie.trailer != null) {
                movie.trailer = newMovie.trailer;
            }
            if (newMovie.movieCharacters != null && !newMovie.movieCharacters.isEmpty()) {
                movie.movieCharacters.addAll(newMovie.movieCharacters);
            }
            status = HttpStatus.OK;
            movieRepository.save(movie);

        } else {
            status = HttpStatus.NOT_FOUND;

        }
        return new ResponseEntity<>(movie, status);
    }

    //Delete movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            status = HttpStatus.OK;

        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(status);

    }
}
