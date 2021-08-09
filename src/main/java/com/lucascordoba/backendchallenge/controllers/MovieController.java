package com.lucascordoba.backendchallenge.controllers;

import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.MovieModel;
import com.lucascordoba.backendchallenge.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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


//    @GetMapping
//    public ResponseEntity<List<MovieDTO>> getMovies(){
//        return ResponseEntity.ok(movieService.listMovies());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findMovie (@PathVariable Long id){
        return ResponseEntity.ok(movieService.findMovie(id));
    }
    @PutMapping
    public ResponseEntity<MovieModel> insertMovie(@RequestBody MovieDTO movieDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.insertMovie(movieDTO));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteMovie(@RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.deleteMovie(movieDTO));
    }
}
