package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public interface CharacterService {
    public List<CharacterDTO> listCharacters();
    public CharacterDTO findCharacter(Long id);
    public CharacterModel insertCharacter(CharacterDTO character);
    public Boolean deleteCharacter(CharacterDTO character);
    public List<CharacterDTO> searchCharacter(String name, Integer age,Long id);

}
