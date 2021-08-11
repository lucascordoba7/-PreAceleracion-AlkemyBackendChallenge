package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterService {
    List<CharacterDTO> listCharacters();
    CharacterDTO findCharacter(Long id);
    CharacterDTO insertCharacter(CharacterDTO character);
    Boolean deleteCharacter(CharacterDTO character);
    Boolean deleteCharacter(Long id);
    List<CharacterDTO> searchCharacter(String name, Integer age, Long id);

}
