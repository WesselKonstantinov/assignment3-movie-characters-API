package com.assignment.moviecharacters.Controllers;

import com.assignment.moviecharacters.Models.Character;
import com.assignment.moviecharacters.Models.Movie;
import com.assignment.moviecharacters.Repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

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


    @PatchMapping("/{id}") //Update character
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character newCharacter) {
        Character character = new Character();
        HttpStatus status;

        if (characterRepository.existsById(id)) {

            Optional<Character> characterRepos = characterRepository.findById(id);
            character = characterRepos.get();

            if (newCharacter.fullName != null) {
                character.fullName = newCharacter.fullName;
            }
            if (newCharacter.alias != null) {
                character.alias = newCharacter.alias;
            }
            if (newCharacter.gender != null) {
                character.gender = newCharacter.gender;
            }
            if (newCharacter.picture != null) {
                character.picture = newCharacter.picture;
            }
            if (newCharacter.movies != null && !newCharacter.movies.isEmpty()) {
                character.movies.addAll(newCharacter.movies);
            }
            status = HttpStatus.OK;
            characterRepository.save(character);

        } else {
            status = HttpStatus.NOT_FOUND;

        }
        return new ResponseEntity<>(character, status);
    }


    @DeleteMapping("/{id}") //Delete Character
    public ResponseEntity<Character> deleteCharacter(@PathVariable Long id) {
        HttpStatus status;

        if (characterRepository.existsById(id)) {
            characterRepository.deleteById(id);
            status = HttpStatus.OK;

        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(status);

    }



}
