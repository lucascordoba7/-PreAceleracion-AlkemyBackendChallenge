package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.MovieModel;

import java.util.List;

public interface MovieService {
    List<MovieDTO> listMovies();
    MovieDTO findMovie(Long id);
    MovieModel insertMovie(MovieDTO movieDTO);
    Boolean deleteMovie(MovieDTO movieDTO);
    List<MovieDTO> searchMovies(String title, Long idGenre, String order);

}
