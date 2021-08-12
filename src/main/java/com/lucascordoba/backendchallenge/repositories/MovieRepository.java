package com.lucascordoba.backendchallenge.repositories;

import com.lucascordoba.backendchallenge.models.MovieModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieModel,Long> {
    List<MovieModel> findByTitleOrGenre_Id(String title, Long idGenre, Sort sort);
}
