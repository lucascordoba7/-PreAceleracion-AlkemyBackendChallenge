package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.GenreDTO;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GenreDTO> listGenres() {
        List<GenreModel> entities = genreRepository.findAll();
        List<GenreDTO> dtos = new ArrayList<>();
        for (GenreModel entity : entities) {
            dtos.add(GenreDTO.simpleFrom(entity));
        }
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDTO findGenre(Long id) {
        GenreModel entity = genreRepository.getById(id);
        return GenreDTO.from(entity);
    }

    @Override
    @Transactional
    public GenreDTO insertGenre(GenreDTO genreDTO) {
        Optional<GenreModel> genreOptional = genreRepository.findByName(genreDTO.getName());
        if (genreOptional.isPresent()) {
            throw new IllegalStateException("duplicated genre name");
        }
        GenreModel entity = genreDTO.buildEntity();
        return GenreDTO.from(genreRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteGenre(GenreDTO genreDTO) {
        if (!genreRepository.existsById(genreDTO.getId()))
            throw new IllegalStateException("Inexistent genre with id: " + genreDTO.getId());
        else
            genreRepository.deleteById(genreDTO.getId());
    }
    @Override
    @Transactional
    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id))
            throw new IllegalStateException("Inexistent genre with id: " + id);
        else
            genreRepository.deleteById(id);
    }
}
