package com.tinosnegocios.financas.services;

import com.tinosnegocios.financas.entities.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    public Movie GetMovie(String title) {

        return new Movie();
    }
}
