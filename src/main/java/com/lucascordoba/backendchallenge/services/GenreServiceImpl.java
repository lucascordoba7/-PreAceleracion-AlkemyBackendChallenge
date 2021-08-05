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
    ModelMapper modelMapper=new ModelMapper();


    @Override
    @Transactional(readOnly = true)
    public List<GenreDTO> listGenres() {
        List<GenreModel> entities=genreRepository.findAll();
        List<GenreDTO> dtos= new ArrayList<>();
        for (GenreModel genre : entities) {
            dtos.add(modelMapper.map(genre,GenreDTO.class));
        }
        return dtos;
    }

}
