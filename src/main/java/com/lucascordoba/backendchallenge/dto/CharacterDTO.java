package com.lucascordoba.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String backgroundHistory;
    private List<MovieDTO> asociatedMovies;

    public CharacterDTO(Long id){
        this.id=id;
    }

    public CharacterModel buildEntity(){
        return CharacterModel.builder()
                .id(this.id)
                .image(this.image)
                .name(this.name)
                .age(this.age)
                .weight(this.weight)
                .backgroundHistory(this.backgroundHistory)
                .asociatedMovies(MovieDTO.dtosToEntities(this.asociatedMovies))
                .build();
    }
    public CharacterModel buildEntity(List<MovieModel> asociatedMovies){
        return CharacterModel.builder()
                .id(this.id)
                .image(this.image)
                .name(this.name)
                .age(this.age)
                .weight(this.weight)
                .backgroundHistory(this.backgroundHistory)
                .asociatedMovies(asociatedMovies)
                .build();
    }
    public static CharacterDTO from(CharacterModel entity){
        return CharacterDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .name(entity.getName())
                .age(entity.getAge())
                .weight(entity.getWeight())
                .backgroundHistory(entity.getBackgroundHistory())
                .asociatedMovies(MovieDTO.entitiesToSimpleDtos(entity.getAsociatedMovies()))
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
        if(entities!=null)
            for(CharacterModel entity: entities){
                dtos.add(from(entity));
            }
        return dtos;
    }
    public static List<CharacterDTO> entitiesToSimpleDtos(List<CharacterModel> entities){
        List<CharacterDTO> dtos=new ArrayList<>();
        if(entities!=null)
            for(CharacterModel entity: entities){
                dtos.add(simpleFrom(entity));
            }
        return dtos;
    }

    public static List<CharacterModel> dtosToEntities(List<CharacterDTO> dtos){
        List<CharacterModel> entities= new ArrayList<>();
        if(dtos!=null)
            for(CharacterDTO dto:dtos){
                entities.add(dto.buildEntity());
            }
        return entities;
    }


}
