package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Franchise;
import com.assignment.moviecharacters.Repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    //Get all franchises
    @GetMapping("/all")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        HttpStatus status = HttpStatus.OK;
        List<Franchise> franchises = franchiseRepository.findAll();
        return new ResponseEntity<>(franchises, status);
    }

    // Get specific franchise
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable Long id) {
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
    @PostMapping("/add")
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        HttpStatus status;

        if (franchise.id != null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(franchise, status);
        }
        status = HttpStatus.OK;
        franchise = franchiseRepository.save(franchise);
        return new ResponseEntity<>(franchise, status);
    }

    //Update franchise
    @PatchMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise newFranchise) {
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

    //Delete franchise
    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable Long id) {
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            franchiseRepository.deleteById(id);
            status = HttpStatus.OK;

        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(status);

    }
}
