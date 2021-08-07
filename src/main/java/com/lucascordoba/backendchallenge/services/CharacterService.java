package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface CharacterService {
    public List<CharacterDTO> listCharacters();

}
