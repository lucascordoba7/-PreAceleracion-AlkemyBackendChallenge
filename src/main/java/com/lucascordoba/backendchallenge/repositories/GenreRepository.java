package com.lucascordoba.backendchallenge.repositories;

import com.lucascordoba.backendchallenge.models.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreModel,Long> {
}
