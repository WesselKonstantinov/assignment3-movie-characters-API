package com.assignment.moviecharacters.Services;

import com.assignment.moviecharacters.Models.Franchise;
import com.assignment.moviecharacters.Models.Movie;
import com.assignment.moviecharacters.Models.MovieCharacter;
import com.assignment.moviecharacters.Repositories.FranchiseRepository;
import com.assignment.moviecharacters.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private MovieRepository movieRepository;

    // Get all franchises
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        HttpStatus status = HttpStatus.OK;
        List<Franchise> franchises = franchiseRepository.findAll();
        return new ResponseEntity<>(franchises, status);
    }

    // Get specific franchise
    public ResponseEntity<Franchise> getFranchise(Long id) {
        Franchise franchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            franchise = franchiseRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(franchise, status);
    }

    // Add franchise
    public ResponseEntity<Franchise> addFranchise(Franchise franchise) {
        HttpStatus status;

        if (franchise.id != null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(franchise, status);
        }
        status = HttpStatus.OK;
        franchise = franchiseRepository.save(franchise);
        return new ResponseEntity<>(franchise, status);
    }

    // Update franchise
    public ResponseEntity<Franchise> updateFranchise(Long id, Franchise newFranchise) {
        Franchise franchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            Optional<Franchise> franchiseRepos = franchiseRepository.findById(id);
            franchise = franchiseRepos.get();

            if (newFranchise.name != null) {
                franchise.name = newFranchise.name;
            }
            if (newFranchise.description != null) {
                franchise.description = newFranchise.description;
            }
            if (newFranchise.movies != null && !newFranchise.movies.isEmpty()) {
                franchise.movies.addAll(newFranchise.movies);
            }
            status = HttpStatus.OK;
            franchiseRepository.save(franchise);

        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(franchise, status);
    }

    // Delete franchise
    public ResponseEntity<Franchise> deleteFranchise(Long id) {
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            Franchise franchise = franchiseRepository.findById(id).get();
            for (Movie movie : franchise.movies) {
                movie.franchise = null;
                movieRepository.save(movie);
            }
            franchiseRepository.deleteById(id);
            status = HttpStatus.OK;

        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(status);
    }

    // Update movies of a given franchise
    public ResponseEntity<Franchise> updateMoviesInFranchise(Long id, Long[] movieIds) {
        Franchise franchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            Optional<Franchise> franchiseRepos = franchiseRepository.findById(id);
            franchise = franchiseRepos.get();

            for (Long movieId : movieIds) {
                if (movieRepository.existsById(movieId)) {
                    Movie movie = movieRepository.findById(movieId).get();

                    if (!franchise.movies.contains(movie)) {
                        franchise.movies.add(movie);
                        movie.franchise = franchise;
                    }
                }
            }

            status = HttpStatus.OK;
            franchiseRepository.save(franchise);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(franchise, status);
    }

    public List<String> getAllMoviesInFranchise(Long id) {
        List<String> moviesInFranchise = franchiseRepository.findById(id).get().getMovies();
        List<String> movieTitle = new ArrayList<>();
        moviesInFranchise.stream().map(

                movie -> movieTitle.add(movie.title)).collect(Collectors.toList());

        return movieTitle;
    }




    public List<String> getAllMovieCharactersInFranchise(Long id) {
        List<String> moviesInFranchise = franchiseRepository.findById(id).get().getMovies();
        List<MovieCharacter> movieCharactersInFranchise = new ArrayList<>();
        List<String> movieCharactersNames = new ArrayList<>();


        moviesInFranchise.stream().map(
                movie -> movieCharactersInFranchise.addAll(movie.getMovieCharacters())
        ).collect(Collectors.toList());

        movieCharactersInFranchise.stream().distinct().map(
                        movieCharacter -> movieCharactersNames.add(movieCharacter.fullName))
                .collect(Collectors.toList());

        return movieCharactersNames;
    }

}



