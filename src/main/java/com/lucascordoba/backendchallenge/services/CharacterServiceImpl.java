package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.dto.ListedCharacterDTO;
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
    private ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ListedCharacterDTO> listCharacters() {
        modelMapper=new ModelMapper();
        List<CharacterModel> entities=characterRepository.findAll();
        List<ListedCharacterDTO> dtos=new ArrayList<>();
        for(CharacterModel entity: entities){
            dtos.add(modelMapper.map(entity, ListedCharacterDTO.class));
        }
        return dtos;
    }



}
