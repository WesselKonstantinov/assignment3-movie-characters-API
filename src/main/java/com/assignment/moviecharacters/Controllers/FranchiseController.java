package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Franchise;
import com.assignment.moviecharacters.Services.FranchiseService;
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
@RequestMapping("/api/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @Operation(summary = "Get all franchises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all franchises",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Franchise.class))})
    })
    @GetMapping("/all")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        return franchiseService.getAllFranchises();
    }

    @Operation(summary = "Get a franchise by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the franchise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class))}),
            @ApiResponse(responseCode = "404", description = "Franchise not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@Parameter(description = "id of franchise to be searched") @PathVariable Long id) {
        return franchiseService.getFranchise(id);
    }

    @Operation(summary = "Get all movie characters in a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all movie characters in the selected franchise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class))}),
            @ApiResponse(responseCode = "404", description = "Franchise and related movie characters not found",
                    content = @Content)
    })
    @GetMapping("/{id}/characters")
    public ResponseEntity<List<String>> getAllMovieCharactersInFranchise(@Parameter(description = "id of franchise to be searched") @PathVariable Long id) {
        return franchiseService.getAllMovieCharactersInFranchise(id);
    }

    @Operation(summary = "Get all movies in a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all movies in the selected franchise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class))}),
            @ApiResponse(responseCode = "404", description = "Franchise and related movies not found",
                    content = @Content)
    })
    @GetMapping("/{id}/movies")
    public ResponseEntity<List<String>> getAllMoviesInFranchise(@Parameter(description = "id of franchise to be searched") @PathVariable Long id) {
        return franchiseService.getAllMoviesInFranchise(id);
    }

    @Operation(summary = "Add a new franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added new franchise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class))})
    })
    @PostMapping("/add")
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        return franchiseService.addFranchise(franchise);
    }

    @Operation(summary = "Update an existing franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the selected franchise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class))}),
            @ApiResponse(responseCode = "404", description = "Franchise not found",
                    content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@Parameter(description = "id of franchise to be updated") @PathVariable Long id, @RequestBody Franchise newFranchise) {
        return franchiseService.updateFranchise(id, newFranchise);
    }

    @Operation(summary = "Update movies in a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated movies in the selected franchise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class))}),
            @ApiResponse(responseCode = "404", description = "Franchise not found",
                    content = @Content)
    })
    @PatchMapping("/{id}/movies/update")
    public ResponseEntity<Franchise> updateMoviesInFranchise(@Parameter(description = "id of franchise to be updated") @PathVariable Long id, @RequestBody Long[] movieIds) {
        return franchiseService.updateMoviesInFranchise(id, movieIds);
    }


    @Operation(summary = "Delete a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the selected franchise",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Franchise not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@Parameter(description = "id of franchise to be deleted") @PathVariable Long id) {
        return franchiseService.deleteFranchise(id);
    }
}
