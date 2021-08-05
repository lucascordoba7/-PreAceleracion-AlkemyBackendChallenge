package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.ListedMovieDTO;
import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.MovieModel;

import java.util.List;

public interface MovieService {
    public List<ListedMovieDTO> listMovies();

}
