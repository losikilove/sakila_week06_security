package com.group01.server_b.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group01.server_b.models.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Query(value = "SELECT * FROM film LIMIT 18446744073709551615 OFFSET 500", nativeQuery = true)
    List<Film> getAllFilms();
}
