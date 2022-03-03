package com.assignment.moviecharacters.Services;

import com.assignment.moviecharacters.Models.Movie;
import com.assignment.moviecharacters.Models.MovieCharacter;
import com.assignment.moviecharacters.Repositories.MovieCharacterRepository;
import com.assignment.moviecharacters.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    // Get all movies
    public ResponseEntity<List<Movie>> getAllMovies() {
        HttpStatus status = HttpStatus.OK;
        List<Movie> movies = movieRepository.findAll();
        return new ResponseEntity<>(movies, status);
    }

    // Get specific movie
    public ResponseEntity<Movie> getMovie(Long id) {
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

    // Add movie
    public ResponseEntity<Movie> addMovie(Movie movie) {
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
    public ResponseEntity<Movie> updateMovie(Long id, Movie newMovie) {
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

    // Delete movie
    public ResponseEntity<Movie> deleteMovie(Long id) {
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            status = HttpStatus.OK;

        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(status);
    }

    // Update movie characters of a given movie
    public ResponseEntity<Movie> updateCharactersInMovie(Long id, Long[] characterIds) {
        Movie movie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            Optional<Movie> movieRepos = movieRepository.findById(id);
            movie = movieRepos.get();

            for (Long characterId : characterIds) {
                if (movieCharacterRepository.existsById(characterId)) {
                    MovieCharacter movieCharacter = movieCharacterRepository.findById(characterId).get();

                    // List of characters will not be updated if movie character is already present
                    if (!movie.movieCharacters.contains(movieCharacter)) {
                        movie.movieCharacters.add(movieCharacter);
                        movieCharacter.movies.add(movie);
                        movieCharacterRepository.save(movieCharacter);
                    }
                }
            }

            status = HttpStatus.OK;
            movieRepository.save(movie);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movie, status);
    }

    public ResponseEntity<List<String>> getAllMovieCharactersInMovie(Long id) {
        Movie movie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            movie = movieRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movie.getCharacters(), status);
    }
}
