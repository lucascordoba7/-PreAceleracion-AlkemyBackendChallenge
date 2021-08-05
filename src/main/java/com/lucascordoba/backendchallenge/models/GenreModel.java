package com.lucascordoba.backendchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "genres")
@NoArgsConstructor
@AllArgsConstructor
public class GenreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column(name = "genre_name")
    private String name;

    @Column(name = "genre_image")
    private String image;

    @OneToMany(mappedBy = "genre",fetch = FetchType.LAZY)
    private List<MovieModel> asociatedMovies;

    public GenreModel (Long id, String name)
    {
        this.id=id;
        this.name=name;
    }

}
