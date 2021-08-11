package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<?> getGenreById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(genreService.findGenre(id));
        } catch (NoSuchElementException noSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noSuchElementException.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> createGenre(@RequestBody GenreDTO genre) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(genreService.insertGenre(genre));
        } catch (InvalidDataAccessApiUsageException d) {
            d.printStackTrace();
            return ResponseEntity.status(400).body(d.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> insertGenre(@PathVariable Long id,@RequestBody GenreDTO genreDTO) {
        genreDTO.setId(id);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(genreService.insertGenre(genreDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteGenre(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.deleteGenre(id));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteGenre(@RequestBody GenreDTO genreDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.deleteGenre(genreDTO));
    }


}

