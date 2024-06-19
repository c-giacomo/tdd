package it.film.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.support.EntityNonStrictReadWriteAccess;
import org.springframework.stereotype.Service;

import it.film.dto.FilmDTO;
import it.film.mapper.FilmMapper;
import it.film.model.Film;
import it.film.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmService {
	
	private final FilmRepository repo;
	private final FilmMapper mapper;
	
	
	public List<FilmDTO> findAll() {
		return mapper.serializzaStream(repo.findAll());
	}
	
	public FilmDTO findById(Integer id) {
		Optional<Film> entity =  repo.findById(id);
		if (entity.isEmpty()) return null;
		return mapper.serializza(entity.get());
	}
	
	public FilmDTO save(FilmDTO film) {
		Film entity = mapper.deserializza(film);
		entity = repo.save(entity);
		return mapper.serializza(entity);
	}
	
	public FilmDTO update(FilmDTO film) {
		Optional<Film> entity = repo.findById(film.getId());
		if (entity.isPresent()) {
			Film result = repo.save(mapper.deserializza(film));
			return mapper.serializza(result);
		} else throw new EntityNotFoundException();
	}
	
	public Boolean delete(Integer id) {
		Optional<Film> entity = repo.findById(id);
		if (entity.isPresent()) {
			repo.delete(entity.get());
			return true;
		} else throw new EntityNotFoundException();
		
	}

}
