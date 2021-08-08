package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface CharacterService {
    public List<CharacterDTO> listCharacters();
    public CharacterDTO findCharacter(Long id);
    public CharacterModel insertCharacter(CharacterDTO character);
    public Boolean deleteCharacter(CharacterDTO character);

}
