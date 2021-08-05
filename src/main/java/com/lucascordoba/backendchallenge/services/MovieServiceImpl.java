package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.ListedMovieDTO;
import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.MovieModel;
import com.lucascordoba.backendchallenge.repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieRepository movieRepository;
    ModelMapper modelMapper=new ModelMapper();
    @Override
    @Transactional(readOnly = true)
    public List<ListedMovieDTO> listMovies() {
        List<MovieModel>entities= movieRepository.findAll();
        List<ListedMovieDTO> dtos=new ArrayList<>();
        for(MovieModel movie: entities)
        {
            dtos.add(modelMapper.map(movie,ListedMovieDTO.class));
        }
        return dtos;
    }


}
