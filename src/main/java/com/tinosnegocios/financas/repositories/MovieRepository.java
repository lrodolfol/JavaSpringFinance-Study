package com.tinosnegocios.financas.repositories;

import com.tinosnegocios.financas.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
