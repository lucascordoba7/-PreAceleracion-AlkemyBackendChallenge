package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.GenreModel;

import java.util.List;

public interface GenreService {
    public List<GenreDTO> listGenres();
    public GenreDTO findGenre(Long id);
    public GenreModel insertGenre(GenreDTO genreDTO);
    public Boolean deleteGenre(GenreDTO genreDTO);
}
