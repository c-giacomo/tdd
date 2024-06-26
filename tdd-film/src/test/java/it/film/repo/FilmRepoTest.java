package it.film.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import it.film.model.Film;
import it.film.repository.FilmRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class FilmRepoTest {
	
	@Autowired
	private FilmRepository repo;
	
	private Film film;
	
	@BeforeEach()
	void setUp() {
		film = new Film("ciao", 10, "anno");
	}
	
	@Test
	void test1() {
		var saved = repo.save(this.film);
		
		assertNotNull(saved);
	}
	
	@Test
	void test2() {
		var saved = repo.save(this.film);
		var found = repo.findById(saved.getId());
		assertTrue(found.isPresent());
		
		assertEquals(saved.getId(), found.get().getId());
	}

	@Test
	void test3() {
		var saved = repo.save(this.film);
		repo.delete(saved);
		
		var optional = repo.findById(saved.getId());
		assertTrue(optional.isEmpty());
	}
}
