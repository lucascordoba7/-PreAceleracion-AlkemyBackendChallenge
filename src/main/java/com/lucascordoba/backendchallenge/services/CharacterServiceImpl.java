package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.repositories.CharacterRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class CharacterServiceImpl implements CharacterService{
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CharacterDTO> listCharacters() {
        return CharacterDTO.entitiesToDtos(characterRepository.findAll());
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


}
