package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import com.lucascordoba.backendchallenge.repositories.CharacterRepository;
import com.lucascordoba.backendchallenge.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CharacterDTO> listCharacters() {
        return CharacterDTO.entitiesToSimpleDtos(characterRepository.findAll());

    }

    @Override
    @Transactional(readOnly = true)
    public CharacterDTO findCharacter(Long id) throws NoSuchElementException {
        CharacterModel entity = characterRepository.findById(id).get();
        return CharacterDTO.from(entity);
    }

    @Override
    public CharacterDTO insertCharacter(CharacterDTO character) {
        List<MovieModel> asociatedMovies = new ArrayList<>();
        if(character.getAsociatedMovies()!=null){
            for (MovieDTO movie : character.getAsociatedMovies()) {
                MovieModel entity=movieRepository.findById(movie.getId()).get();
                asociatedMovies.add(entity);
            }
        }
        CharacterModel entity = character.buildEntity(asociatedMovies);
        return CharacterDTO.from(characterRepository.save(entity));
    }

    @Override
    public Boolean deleteCharacter(CharacterDTO character) {
        try {
            characterRepository.deleteById(character.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteCharacter(Long id) {
        try {
            characterRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CharacterDTO> searchCharacter(String name, Integer age, Long id) {

        MovieModel movie = new MovieModel(0L);
        if (id != null)
            movie = movieService.findMovie(id).buildEntity();
        List<CharacterModel> entities = characterRepository.findByNameOrAgeOrAsociatedMovies(name, age, movie);
        return CharacterDTO.entitiesToDtos(entities);
    }


}
