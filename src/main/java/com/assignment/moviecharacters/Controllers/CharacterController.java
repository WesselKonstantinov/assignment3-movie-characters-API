package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Character;
import com.assignment.moviecharacters.Repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping("/all") //Get all characters
    public ResponseEntity<List<Character>> getAllCharacters() {
        HttpStatus status = HttpStatus.OK;
        List<Character> characters = characterRepository.findAll();
        return new ResponseEntity<>(characters, status);
    }

}
