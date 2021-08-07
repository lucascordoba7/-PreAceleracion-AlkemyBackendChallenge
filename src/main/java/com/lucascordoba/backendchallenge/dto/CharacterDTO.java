package com.lucascordoba.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDTO {

    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String BackgroundHistory;
    private List<MovieDTO> asociatedMovies;

    public CharacterModel buildEntity(){
        return CharacterModel.builder()
                .id(this.id)
                .image(this.image)
                .name(this.name)
                .age(this.age)
                .weight(this.weight)
                .BackgroundHistory(this.BackgroundHistory)
                .asociatedMovies(MovieDTO.dtosToEntities(this.asociatedMovies))
                .build();
    }
    public static CharacterDTO from(CharacterModel entity){
        return CharacterDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .name(entity.getName())
                .age(entity.getAge())
                .weight(entity.getWeight())
                .BackgroundHistory(entity.getBackgroundHistory())
                .asociatedMovies(MovieDTO.entitiesToDtos(entity.getAsociatedMovies()))
                .build();
    }
    public static CharacterDTO simpleFrom(CharacterModel entity){
        return CharacterDTO.builder()
                .name(entity.getName())
                .image(entity.getImage())
                .build();
    }
    public static List<CharacterDTO> entitiesToDtos(List<CharacterModel> entities){
        List<CharacterDTO> dtos=new ArrayList<>();
        for(CharacterModel entity: entities){
            dtos.add(from(entity));
        }
        return dtos;
    }
    public static List<CharacterDTO> entitiesToSimpleDtos(List<CharacterModel> entities){
        List<CharacterDTO> dtos=new ArrayList<>();
        for(CharacterModel entity: entities){
            dtos.add(simpleFrom(entity));
        }
        return dtos;
    }

    public static List<CharacterModel> dtosToEntities(List<CharacterDTO> dtos){
        List<CharacterModel> entities= new ArrayList<>();
        for(CharacterDTO dto:dtos){
            entities.add(dto.buildEntity());
        }
        return entities;
    }


}
