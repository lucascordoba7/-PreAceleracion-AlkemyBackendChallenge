package com.lucascordoba.backendchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "characters")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;

    @Column(name = "character_image")
    private String image;

    @Column(name = "character_name")
    private String name;

    @Column(name = "character_age")
    private Integer age;

    @Column(name = "character_weight")
    private Double weight;

    @Column(name = "character_history")
    private String BackgroundHistory;
    @ManyToMany(mappedBy = "asociatedCharacters",cascade = CascadeType.ALL)
    private List<MovieModel> asociatedMovies;

}
