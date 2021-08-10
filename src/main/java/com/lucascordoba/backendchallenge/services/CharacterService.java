package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterService {
    List<CharacterDTO> listCharacters();
    CharacterDTO findCharacter(Long id);
    CharacterModel insertCharacter(CharacterDTO character);
    Boolean deleteCharacter(CharacterDTO character);
    List<CharacterDTO> searchCharacter(String name, Integer age, Long id);

}
