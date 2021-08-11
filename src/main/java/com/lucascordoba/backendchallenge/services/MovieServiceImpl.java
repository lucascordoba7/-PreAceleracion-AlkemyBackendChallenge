package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.MovieModel;
import com.lucascordoba.backendchallenge.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDTO> listMovies() {
        List<MovieModel> entities = movieRepository.findAll();
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieModel entity : entities) {
            dtos.add(MovieDTO.simpleFrom(entity));
        }
        return dtos;
    }

    @Override
    public MovieDTO findMovie(Long id) {
        return MovieDTO.from(movieRepository.findById(id).get());
    }

    @Override
    public MovieModel insertMovie(MovieDTO movieDTO) {
        MovieModel entity = movieDTO.buildEntity();
        return movieRepository.save(entity);
    }

    @Override
    public Boolean deleteMovie(MovieDTO movieDTO) {
        MovieModel entity = movieDTO.buildEntity();
        try {
            movieRepository.deleteById(entity.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<MovieDTO> searchMovies(String title, Long idGenre, String order) {
        List<MovieModel> entities = null;
        if(order!=null)
            order=order.toLowerCase().trim();
        entities =
                "desc".equals(order) ?
                        movieRepository.findByTitleOrGenre_Id(title, idGenre, Sort.by(Sort.Direction.DESC, "id"))
                        :
                        movieRepository.findByTitleOrGenre_Id(title, idGenre, Sort.by(Sort.Direction.ASC, "id"));
        return MovieDTO.entitiesToDtos(entities);
    }


}
