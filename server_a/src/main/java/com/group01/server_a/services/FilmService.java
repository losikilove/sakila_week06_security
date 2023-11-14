package com.group01.server_a.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group01.server_a.models.Film;
import com.group01.server_a.repositories.FilmRepository;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAll() {
        return filmRepository.getAllFilms();
    }
}
