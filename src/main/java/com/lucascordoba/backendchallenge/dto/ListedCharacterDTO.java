package com.lucascordoba.backendchallenge.dto;

import com.lucascordoba.backendchallenge.models.MovieModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Data
public class ListedCharacterDTO implements Serializable {
    private String image;
    private String name;
}
