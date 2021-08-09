package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;


    @GetMapping
    public ResponseEntity<?> listCharacters(
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "movie",required = false) Long idMovie) {
        //Map<String,Object> queryParam=buildQueryMap(name,age,idMovie);
        List<CharacterDTO> result;
        if(age==null && name == null && idMovie==null)
            return ResponseEntity.ok(characterService.listCharacters());
        try {
            result = characterService.searchCharacter(name, age,idMovie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> findCharacter(@PathVariable Long id) {
        return ResponseEntity.ok(characterService.findCharacter(id));
    }

    @PutMapping
    public ResponseEntity<CharacterModel> insertCharacter(@RequestBody CharacterDTO characterDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.insertCharacter(characterDTO));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCharacter(@RequestBody CharacterDTO characterDTO) {
        return ResponseEntity.ok(characterService.deleteCharacter(characterDTO));
    }
}
