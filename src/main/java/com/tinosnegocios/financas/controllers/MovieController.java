package com.tinosnegocios.financas.controllers;

import com.tinosnegocios.financas.entities.Movie;
import com.tinosnegocios.financas.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<Movie> GetMovie(String title, boolean persist){
        Movie movie = service.getMovie(title, persist);

        return ResponseEntity.ok().body(movie);
    }

    @PostMapping()
    public ResponseEntity<Movie> PostMovie(String title){
        Movie movie = service.getAndPersistMovie(title);

        return ResponseEntity.ok().body(movie);
    }
}
