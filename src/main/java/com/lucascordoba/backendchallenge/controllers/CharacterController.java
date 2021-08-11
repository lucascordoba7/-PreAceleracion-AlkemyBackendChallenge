package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;


    @GetMapping
    public ResponseEntity<?> listCharacters(
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "movie", required = false) Long idMovie) {
        List<CharacterDTO> result;
        if ((age == null) && idMovie == null && (name == null || "".equals(name)))
            return ResponseEntity.ok(characterService.listCharacters());
        try {
            result = characterService.searchCharacter(name, age, idMovie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCharacter(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(characterService.findCharacter(id));
        } catch (NoSuchElementException noSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noSuchElementException.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createCharacter(@RequestBody CharacterDTO character) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(characterService.insertCharacter(character));
        } catch (InvalidDataAccessApiUsageException d) {
            d.printStackTrace();
            return ResponseEntity.status(400).body(d.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> insertCharacter(@PathVariable Long id, @RequestBody CharacterDTO character) {
        character.setId(id);
        try {
            return ResponseEntity.ok(characterService.insertCharacter(character));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteCharacter(@RequestBody CharacterDTO characterDTO) {
        return ResponseEntity.ok(characterService.deleteCharacter(characterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCharacter(@PathVariable Long id) {
        return ResponseEntity.ok(characterService.deleteCharacter(id));
    }
}
