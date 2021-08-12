package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.MovieModel;
import com.lucascordoba.backendchallenge.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<?> listMovies(
            @RequestParam (value = "name",required = false) String name,
            @RequestParam (value = "genre",required = false) Long idGenre,
            @RequestParam(value = "order",required = false) String order
    ){
        List<MovieDTO> result;
        if (name==null && idGenre==null)
            return ResponseEntity.ok(movieService.listMovies());
        try {
            result=movieService.searchMovies(name,idGenre,order);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
         return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findMovie (@PathVariable Long id){
        try {
            return ResponseEntity.ok(movieService.findMovie(id));
        }catch (NoSuchElementException ns){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ns.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody MovieDTO movie){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(movieService.insertMovie(movie));
        }catch (InvalidDataAccessApiUsageException d){
            return ResponseEntity.status(400).body(d.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> insertMovie(@PathVariable Long id,@RequestBody MovieDTO movieDTO){
        movieDTO.setId(id);
        try {
            return ResponseEntity.ok(movieService.insertMovie(movieDTO));
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteMovie(@RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.deleteMovie(movieDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMovie(@PathVariable Long id){
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }
}
