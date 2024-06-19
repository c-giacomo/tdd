package it.film.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import it.film.dto.FilmDTO;
import it.film.model.Film;

@ExtendWith(MockitoExtension.class)
class FilmMapperTest {
	
	@InjectMocks
	private FilmMapperImpl mapper;

	@Test
	void test1() {
		Film film = new Film("Blade 2", 8, "2020-08-01");
		
		FilmDTO result = mapper.serializza(film);
		
		assertEquals(result.getTitle(), "Blade 2");
		assertEquals(result.getValutation(), 8);
		assertEquals(result.getReleaseDate(), "2020-08-01");
	}

}
