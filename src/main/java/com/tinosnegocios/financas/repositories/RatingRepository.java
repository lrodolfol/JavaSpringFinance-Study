package com.tinosnegocios.financas.repositories;

import com.tinosnegocios.financas.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
