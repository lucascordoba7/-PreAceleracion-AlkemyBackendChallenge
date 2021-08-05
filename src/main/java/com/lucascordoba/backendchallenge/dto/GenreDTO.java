package com.lucascordoba.backendchallenge.dto;

import com.lucascordoba.backendchallenge.models.MovieModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class GenreDTO implements Serializable {

    private Long id;
    private String name;
    private String image;
    //private List<MovieModel> asociatedMovies;
}
