package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;


    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getCharacters(){
        return ResponseEntity.ok(characterService.listCharacters());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> findCharacter(@PathVariable Long id){
        return ResponseEntity.ok(characterService.findCharacter(id));
    }
    @PutMapping
    public ResponseEntity<CharacterModel> insertCharacter(@RequestBody CharacterDTO characterDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.insertCharacter(characterDTO));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteCharacter(@RequestBody CharacterDTO characterDTO){
        return ResponseEntity.ok(characterService.deleteCharacter(characterDTO));
    }
}
