package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Movie;
import com.assignment.moviecharacters.Models.MovieCharacter;
import com.assignment.moviecharacters.Repositories.MovieRepository;
import com.assignment.moviecharacters.Services.MovieService;
import com.assignment.moviecharacters.Services.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;


    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie newMovie) {
        return movieService.updateMovie(id, newMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
       return movieService.deleteMovie(id);
    }

    @GetMapping("/{title}/characters")
    public ResponseEntity<Response> getAllMovieCharactersInMovie(@PathVariable String title){
        List<String> characterNames;
        HttpStatus status;
        Response response = new Response();
        
        if(movieRepository.movieByTitleExists(title)){
            status = HttpStatus.OK;
            characterNames = movieService.getAllMovieCharactersInMovie(title);
            Response.data = String.valueOf(characterNames);
            Response.message ="All character in: " + title;

        } else {
            status = HttpStatus.NOT_FOUND;
            Response.data = null;
            Response.message = "movie not in library";
        }

        return new ResponseEntity<>(response, status);
    }
}
