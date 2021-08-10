package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.GenreModel;

import java.util.List;

public interface GenreService {
    List<GenreDTO> listGenres();
    GenreDTO findGenre(Long id);
    GenreModel insertGenre(GenreDTO genreDTO);
    Boolean deleteGenre(GenreDTO genreDTO);
}
