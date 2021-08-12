package com.lucascordoba.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO implements Serializable {
    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private Integer rating;
    private GenreDTO genre;
    private List<CharacterDTO> asociatedCharacters;

    public MovieDTO(Long id){
        this.id=id;
    }
    public MovieModel buildEntity(){
        return MovieModel.builder()
                .id(this.id)
                .image(this.image)
                .title(this.title)
                .creationDate(this.creationDate)
                .rating(this.rating)
                .genre(this.genre.buildEntity())
                .asociatedCharacters(CharacterDTO.dtosToEntities(this.asociatedCharacters))
                .build();
    }
    public MovieModel buildEntity(List<CharacterModel> asociatedCharacters, GenreModel genre){
        return MovieModel.builder()
                .id(this.id)
                .image(this.image)
                .title(this.title)
                .creationDate(this.creationDate)
                .rating(this.rating)
                .genre(genre)
                .asociatedCharacters(asociatedCharacters)
                .build();
    }
    public static MovieDTO from(MovieModel entity){
        return MovieDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .title(entity.getTitle())
                .creationDate(entity.getCreationDate())
                .rating(entity.getRating())
                .genre(GenreDTO.simpleFrom(entity.getGenre()))
                .asociatedCharacters(CharacterDTO.entitiesToSimpleDtos(entity.getAsociatedCharacters()))
                .build();
    }
    public static MovieDTO simpleFrom(MovieModel entity){
        return MovieDTO.builder()
                .title(entity.getTitle())
                .image(entity.getImage())
                .creationDate(entity.getCreationDate())
                .build();
    }
    public static List<MovieDTO> entitiesToDtos(List<MovieModel> entities){
        List<MovieDTO> dtos=new ArrayList<>();
        if (entities!=null)
            for(MovieModel entity : entities){
                dtos.add(from(entity));
            }
        return dtos;
    }
    public static List<MovieDTO> entitiesToSimpleDtos(List<MovieModel> entities){
        List<MovieDTO> dtos=new ArrayList<>();
        if (entities!=null)
            for(MovieModel entity : entities){

                dtos.add(simpleFrom(entity));
            }
        return dtos;
    }
    public static List<MovieModel> dtosToEntities(List<MovieDTO> dtos){
        List<MovieModel> entities=new ArrayList<>();
        if(dtos!= null)
            for(MovieDTO dto : dtos){
                entities.add(dto.buildEntity());
            }
        return entities;
    }

}
