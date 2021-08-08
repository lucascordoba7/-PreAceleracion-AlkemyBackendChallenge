package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenres() {
        return ResponseEntity.ok(genreService.listGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.findGenre(id));
    }
    @PutMapping
    public ResponseEntity<GenreModel> insertGenre(@RequestBody GenreDTO genreDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.insertGenre(genreDTO));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteGenre(@RequestBody GenreDTO genreDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genreService.deleteGenre(genreDTO));
    }



}

