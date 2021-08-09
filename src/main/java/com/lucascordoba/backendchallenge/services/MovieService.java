package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.MovieModel;

import java.util.List;

public interface MovieService {
    public List<MovieDTO> listMovies();
    public MovieDTO findMovie(Long id);
    public MovieModel insertMovie(MovieDTO movieDTO);
    public Boolean deleteMovie(MovieDTO movieDTO);
    public List<MovieDTO> searchMovies(String title,Long idGenre,String order);

}
