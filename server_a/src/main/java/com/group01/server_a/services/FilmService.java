package com.group01.server_a.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.group01.server_a.models.Film;
import com.group01.server_a.repositories.FilmRepository;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAll() {
        try {
            List<Film> listFilms = filmRepository.getAllFilms();
            String url = "http://localhost:8082/v2/api/film/get-all";
            RestTemplate restTemplate = new RestTemplate();
            Film[] listRestFilms = restTemplate.postForObject(url, null, Film[].class);

            for (Film film : listRestFilms) {
                listFilms.add(film);
            }

            return listFilms;
        } catch (Exception e) {
            return null;
        }
    }
}
