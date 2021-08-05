package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenres(){
        return ResponseEntity.ok(genreService.listGenres());
    }
    @PostMapping
    public ResponseEntity<GenreModel> getGenreById(@RequestBody GenreDTO genreDTO){
        return ResponseEntity.ok(genreService.findGenre(genreDTO));
    }
    @PutMapping
    public ResponseEntity<GenreModel> createGenre(@RequestBody GenreDTO genreDTO){

        return ResponseEntity.ok(genreService.insertGenre(genreDTO));
    }
    @DeleteMapping
    public ResponseEntity deleteGenre(@RequestBody GenreDTO genreDTO){
        return ResponseEntity.ok(genreService.deleteGenre(genreDTO));
    }
}
