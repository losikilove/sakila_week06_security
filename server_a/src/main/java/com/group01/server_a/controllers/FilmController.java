package com.group01.server_a.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group01.server_a.models.Film;
import com.group01.server_a.services.FilmService;

@RestController
@RequestMapping("/v1/api/film")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/get-all")
    public ResponseEntity<List<Film>> getAll() {
        return new ResponseEntity<>(filmService.getAll(), HttpStatus.OK);
    }
}
