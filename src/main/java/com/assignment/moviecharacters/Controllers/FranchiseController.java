package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Franchise;
import com.assignment.moviecharacters.Services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @GetMapping("/all")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        return franchiseService.getAllFranchises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable Long id) {
        return franchiseService.getFranchise(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        return franchiseService.addFranchise(franchise);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise newFranchise) {
        return franchiseService.updateFranchise(id, newFranchise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable Long id) {
        return franchiseService.deleteFranchise(id);
    }
}
