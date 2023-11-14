package com.group01.server_b.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group01.server_b.models.Film;
import com.group01.server_b.repositories.FilmRepository;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAll() {
        return filmRepository.getAllFilms();
        // return filmRepository.findAll();
    }
}
