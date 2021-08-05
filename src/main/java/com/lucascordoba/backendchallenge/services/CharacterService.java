package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.dto.ListedCharacterDTO;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface CharacterService {
    public List<ListedCharacterDTO> listCharacters();

}
