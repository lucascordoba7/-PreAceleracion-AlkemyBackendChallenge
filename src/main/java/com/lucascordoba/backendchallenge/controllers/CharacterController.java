package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.ListedCharacterDTO;
import com.lucascordoba.backendchallenge.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;


    @GetMapping
    public ResponseEntity<List<ListedCharacterDTO>> getCharacters(){
        return ResponseEntity.ok(characterService.listCharacters());
    }
}
