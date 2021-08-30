package com.lucascordoba.backendchallenge.repositories;

import com.lucascordoba.backendchallenge.models.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<GenreModel,Long> {
    Optional<GenreModel> findByName(String name);

}
