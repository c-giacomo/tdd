package it.film.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.film.dto.FilmDTO;
import it.film.service.FilmService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmResource {
	
	private final FilmService service;
	
	@GetMapping
	public ResponseEntity<List<FilmDTO>> findAll() {
		List<FilmDTO> result = service.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FilmDTO> findById(@PathVariable("id") Integer id) {
		FilmDTO result = service.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<FilmDTO> save(@RequestBody FilmDTO film) {
		FilmDTO result = service.save(film);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
