package com.lucascordoba.backendchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name = "movie_image")
    private String image;

    @Column(name = "movie_title")
    private String title;

    @Column(name = "movie_date")
    private LocalDate creationDate;

    @Column(name = "movie_rating")
    private int rating;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private GenreModel genre;

    @ManyToMany
    @JoinTable(name = "characters_movies",joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<CharacterModel> asociatedCharacters;

    public MovieModel (Long id, String image,String title,LocalDate creationDate,Integer rating){
        this.id=id;
        this.image=image;
        this.title=title;
        this.creationDate=creationDate;
        this.rating=rating;
    }
    public MovieModel (Long id){
        this.id=id;
    }
}
