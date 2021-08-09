package com.lucascordoba.backendchallenge.repositories;

import com.lucascordoba.backendchallenge.dto.MovieDTO;
import com.lucascordoba.backendchallenge.models.CharacterModel;
import com.lucascordoba.backendchallenge.models.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel,Long> {

    List<CharacterModel> findByNameOrAgeOrAsociatedMovies(String name, Integer age,MovieModel movieModel);

}
