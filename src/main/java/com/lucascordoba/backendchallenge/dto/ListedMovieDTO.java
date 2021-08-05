package com.lucascordoba.backendchallenge.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ListedMovieDTO {
    private String image;
    private String title;
    private LocalDate creationDate;
}
