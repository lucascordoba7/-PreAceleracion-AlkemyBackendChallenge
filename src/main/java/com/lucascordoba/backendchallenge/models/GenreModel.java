package com.lucascordoba.backendchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "genres")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column(name = "genre_name",unique = true,nullable = false)
    private String name;

    @Column(name = "genre_image")
    private String image;

    @OneToMany(mappedBy = "genre",fetch = FetchType.LAZY)
    private List<MovieModel> asociatedMovies;

}
