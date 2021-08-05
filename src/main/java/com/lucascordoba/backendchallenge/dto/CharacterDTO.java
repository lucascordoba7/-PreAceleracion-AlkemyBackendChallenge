package com.lucascordoba.backendchallenge.dto;

import com.lucascordoba.backendchallenge.models.MovieModel;
import lombok.Data;

import java.util.List;

@Data
public class CharacterDTO {

    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String BackgroundHistory;
    private List<MovieModel> asociatedMovies;


}
