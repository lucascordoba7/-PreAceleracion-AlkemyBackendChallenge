package com.lucascordoba.backendchallenge.dto;

import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.GenreModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
public class MovieDTO implements Serializable {
    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private int rating;
    private GenreDTO genre;
    //private List<CharacterModel> asociatedCharacters;
}
