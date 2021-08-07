package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    GenreRepository genreRepository;
    @Override
    @Transactional(readOnly = true)
    public List<GenreDTO> listGenres() {
        List<GenreModel> entities=genreRepository.findAll();
        List<GenreDTO> dtos= new ArrayList<>();
        for (GenreModel entity : entities) {
            dtos.add(GenreDTO.from(entity));
        }
        return dtos;
    }

    @Override
    public GenreModel findGenre(GenreDTO genre) {
        GenreModel entity=genreRepository.getById(genre.getId());
        return entity;
    }
    @Override
    public GenreModel findGenre(Long id) {
        GenreModel entity=genreRepository.getById(id);
        return entity;
    }

    @Override
    public GenreModel insertGenre(GenreDTO genre) {
        return genreRepository.save(genre.buildEntity());
    }

    @Override
    public Boolean deleteGenre(GenreDTO genre) {
        GenreModel entity=genre.buildEntity();
        try {
            genreRepository.deleteById(entity.getId());
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }


}
