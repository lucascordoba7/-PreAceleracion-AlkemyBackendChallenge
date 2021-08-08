package com.lucascordoba.backendchallenge.services;

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
    @Override
    @Transactional(readOnly = true)
    public List<MovieDTO> listMovies() {
        List<MovieModel>entities= movieRepository.findAll();
        List<MovieDTO> dtos=new ArrayList<>();
        for(MovieModel entity: entities)
        {
            dtos.add(MovieDTO.from(entity));
        }
        return dtos;
    }

    @Override
    public MovieDTO findMovie(Long id) {
        MovieModel entity=movieRepository.findById(id).orElse(new MovieModel());
        return MovieDTO.from(entity);
    }

    @Override
    public MovieModel insertMovie(MovieDTO movieDTO) {
        MovieModel entity=movieDTO.buildEntity();
        return movieRepository.save(entity);
    }

    @Override
    public Boolean deleteMovie(MovieDTO movieDTO) {
        MovieModel entity=movieDTO.buildEntity();
        try {
            movieRepository.deleteById(entity.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
