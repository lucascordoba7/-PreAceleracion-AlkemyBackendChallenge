package com.lucascordoba.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucascordoba.backendchallenge.models.GenreModel;
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
    private List<MovieDTO> asociatedMovies;

    public GenreModel buildEntity() {
        return GenreModel.builder()
                .id(this.id)
                .name(this.name)
                .image(this.image)
                .asociatedMovies(MovieDTO.dtosToEntities(this.asociatedMovies))
                .build();
    }

    public static GenreDTO from(GenreModel entity) {
        return GenreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .asociatedMovies(MovieDTO.entitiesToDtos(entity.getAsociatedMovies()))
                .build();
    }

    public static GenreDTO simpleFrom(GenreModel entity) {
        return GenreDTO.builder()
                .name(entity.getName())
                .build();
    }


}
