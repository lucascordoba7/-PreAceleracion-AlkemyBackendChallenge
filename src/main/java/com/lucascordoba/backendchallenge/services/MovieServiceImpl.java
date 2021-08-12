package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.CharacterDTO;
import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.GenreModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import com.lucascordoba.backendchallenge.repositories.CharacterRepository;
import com.lucascordoba.backendchallenge.repositories.GenreRepository;
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
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    GenreRepository genreRepository;

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
    public MovieDTO insertMovie(MovieDTO movieDTO) {
        List<CharacterModel> asociatedCharacters=new ArrayList<>();
        if(movieDTO.getAsociatedCharacters()!=null){
            for (CharacterDTO character:movieDTO.getAsociatedCharacters()){
                CharacterModel entity=characterRepository.findById(character.getId()).get();
                asociatedCharacters.add(entity);
            }
        }
        GenreModel entityGenre=genreRepository.findById(movieDTO.getGenre().getId()).get();

        MovieModel entity = movieDTO.buildEntity(asociatedCharacters,entityGenre);
        return MovieDTO.from(movieRepository.save(entity));
    }

    @Override
    public Boolean deleteMovie(MovieDTO movieDTO) {
        try {
            movieRepository.deleteById(movieDTO.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Boolean deleteMovie(Long id) {
        try {
            movieRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<MovieDTO> searchMovies(String title, Long idGenre, String order) {
        List<MovieModel> entities = null;

        order=(order!=null)?order.toLowerCase().trim() : null;

        entities =
                "desc".equals(order) ?
                        movieRepository.findByTitleOrGenre_Id(title, idGenre, Sort.by(Sort.Direction.DESC, "id"))
                        :
                        movieRepository.findByTitleOrGenre_Id(title, idGenre, Sort.by(Sort.Direction.ASC, "id"));
        return MovieDTO.entitiesToDtos(entities);
    }


}
