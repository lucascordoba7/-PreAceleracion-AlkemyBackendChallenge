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
            dtos.add(GenreDTO.simpleFrom(entity));
        }
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDTO findGenre(Long id) {
        GenreModel entity=genreRepository.getById(id);
        return GenreDTO.from(entity);
    }

    @Override
    @Transactional
    public GenreDTO insertGenre(GenreDTO genreDTO) {
        GenreModel entity=genreDTO.buildEntity();
        return GenreDTO.from(genreRepository.save(entity));
    }

    @Override
    @Transactional
    public Boolean deleteGenre(GenreDTO genreDTO) {
        try {
            genreRepository.deleteById(genreDTO.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Boolean deleteGenre(Long id) {
        try {
            genreRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
