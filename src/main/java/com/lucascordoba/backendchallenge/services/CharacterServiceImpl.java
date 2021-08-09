package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import com.lucascordoba.backendchallenge.repositories.CharacterRepository;
import com.lucascordoba.backendchallenge.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CharacterServiceImpl implements CharacterService{
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MovieService movieService;

    @Override
    @Transactional(readOnly = true)
    public List<CharacterDTO> listCharacters() {
        return CharacterDTO.entitiesToSimpleDtos(characterRepository.findAll());

    }

    @Override
    @Transactional(readOnly = true)
    public CharacterDTO findCharacter(Long id) {
        CharacterModel entity=characterRepository.findById(id).orElse(null);
        return CharacterDTO.from(entity);
    }

    @Override
    public CharacterModel insertCharacter(CharacterDTO character) {
        CharacterModel entity=character.buildEntity();
        return characterRepository.save(entity);
    }

    @Override
    public Boolean deleteCharacter(CharacterDTO character) {
        CharacterModel entity=character.buildEntity();
        try{
            characterRepository.deleteById(entity.getId());
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<CharacterDTO> searchCharacter(String name,Integer age,Long id) {

        MovieModel movie=new MovieModel(0L);
        if(id!=null)
            movie=movieService.findMovie(id).buildEntity();
        List<CharacterModel> entities=characterRepository.findByNameOrAgeOrAsociatedMovies(name,age,movie);
        return CharacterDTO.entitiesToDtos(entities);
    }


}
