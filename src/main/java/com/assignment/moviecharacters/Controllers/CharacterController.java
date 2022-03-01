package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Character;
import com.assignment.moviecharacters.Repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;


    //add character
    @PostMapping("/add_character")
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        HttpStatus status;

        if (character.id != null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(character, status);
        }
        status = HttpStatus.OK;
        character = characterRepository.save(character);
        return new ResponseEntity<>(character, status);
    }

    @GetMapping("/all") //Get all characters
    public ResponseEntity<List<Character>> getAllCharacters() {
        HttpStatus status = HttpStatus.OK;
        List<Character> characters = characterRepository.findAll();
        return new ResponseEntity<>(characters, status);
    }



}
