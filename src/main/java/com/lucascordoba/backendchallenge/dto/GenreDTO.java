package com.lucascordoba.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDTO implements Serializable {

    private Long id;
    private String name;
    private String image;
    private List<MovieModel> asociatedMovies;

    public GenreModel buildEntity(){
        return GenreModel.builder()
                .id(this.id)
                .name(this.name)
                .image(this.image)
                .asociatedMovies(this.asociatedMovies)
                .build();
    }
    public static GenreDTO from(GenreModel entity){
        return GenreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .asociatedMovies(entity.getAsociatedMovies())
                .build();

    }


}
