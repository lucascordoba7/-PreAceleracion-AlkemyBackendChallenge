package com.lucascordoba.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO implements Serializable {
    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private Integer rating;
    private GenreDTO genre;
    private List<CharacterModel> asociatedCharacters;

    public MovieModel buildEntity(){
        return MovieModel.builder()
                .id(this.id)
                .image(this.image)
                .title(this.title)
                .creationDate(this.creationDate)
                .rating(this.rating)
                .genre(this.genre.buildEntity())
                .asociatedCharacters(this.asociatedCharacters)
                .build();
    }
    public static MovieDTO from(MovieModel entity){
        return MovieDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .title(entity.getTitle())
                .creationDate(entity.getCreationDate())
                .rating(entity.getRating())
                .genre(GenreDTO.from(entity.getGenre()))
                .asociatedCharacters(entity.getAsociatedCharacters())
                .build();
    }

}
