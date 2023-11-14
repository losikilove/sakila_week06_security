package com.group01.server_a.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group01.server_a.models.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Query(value = "SELECT * FROM film LIMIT 500 OFFSET 0", nativeQuery = true)
    List<Film> getAllFilms();
}
