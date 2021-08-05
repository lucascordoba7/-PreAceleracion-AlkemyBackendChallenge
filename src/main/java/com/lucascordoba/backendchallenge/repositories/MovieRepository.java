package com.lucascordoba.backendchallenge.repositories;

import com.lucascordoba.backendchallenge.models.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieModel,Long> {
}
