package it.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.film.model.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {

}
