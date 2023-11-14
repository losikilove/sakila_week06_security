package com.group01.server_a.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group01.server_a.models.Actor;
import com.group01.server_a.services.ActorService;

@RestController
@RequestMapping("/v1/api/actor")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/get-all")
    ResponseEntity<List<Actor>> getAll() {
        return new ResponseEntity<>(actorService.getAll(), HttpStatus.OK);
    }
}
