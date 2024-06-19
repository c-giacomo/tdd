package it.film.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
