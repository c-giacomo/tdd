package it.film.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import it.film.dto.FilmDTO;
import it.film.mapper.FilmMapper;
import it.film.model.Film;
import it.film.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class FilmServiceTest {
	
	@Mock
	private FilmRepository repo;
	
	@Mock
	private FilmMapper mapper;
	
	@InjectMocks
	private FilmService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(repo);
		MockitoAnnotations.openMocks(mapper);
	}
	
	@Test
	void findAll() {
		List<Film> entities = List.of(new Film("Blade 2", 8, "2020-10-10"),
									  new Film("Blade Runner", 10, "1985-10-10"));
		
		List<FilmDTO> dto = List.of(new FilmDTO("Blade 2", 8, "2020-10-10"),
				  new FilmDTO("Blade Runner", 10, "1985-10-10"));
		
		Mockito.when(repo.findAll()).thenReturn(entities);
		
		Mockito.when(mapper.serializzaStream(entities)).thenReturn(dto);
		
		List<FilmDTO> result = service.findAll();
		
		assertEquals(2, result.size());
		assertEquals("Blade 2", result.get(0).getTitle());
		assertEquals(10, result.get(1).getValutation());
	}
	
	@Test
	void findById() {
		Film entity = new Film(1, "Il Padrino", 10, "1950-10-10");
		FilmDTO dto = new FilmDTO(1, "Il Padrino", 10, "1950-10-10");
		
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(entity));
		
		Mockito.when(mapper.serializza(entity)).thenReturn(dto);
		
		FilmDTO result = service.findById(1);
		
		assertNotNull(result);
	}
	
	@Test
	void findByIdNull() {
		Mockito.when(repo.findById(99)).thenReturn(Optional.empty());
		
		FilmDTO result = service.findById(99);
		
		assertEquals(null, result);
	}
	
	@Test
	void save() {
		FilmDTO dto = new FilmDTO(1, "Il Padrino", 10, "1950-10-10");
		Film entity = new Film(1, "Il Padrino", 10, "1950-10-10");
		
		Mockito.when(mapper.deserializza(dto)).thenReturn(entity);
		
		Mockito.when(repo.save(entity)).thenReturn(entity);
		
		Mockito.when(mapper.serializza(entity)).thenReturn(dto);
		
		FilmDTO saved = service.save(dto);
		
		assertNotNull(saved);
	}
	
	@Test
	void delete() {
		Integer id = 9;
		Film entity = new Film(id, "Il Padrino", 10, "1950-10-10");
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(entity));
		
		Boolean result = service.delete(9);
		
		assertEquals(true, result);
	}
	
	@Test
	void tryingDeleteButNotFound() {
		
		assertThrows(EntityNotFoundException.class, () -> service.delete(5));
	}
	
	@Test
	void update() {
		Film entity = new Film(1, "Il Padrino", 9, "1950-10-10");
		FilmDTO toUpdateDto = new FilmDTO(1, "Il Padrino", 10, "1950-10-10");
		Film toUpdateEntity = new Film(1, "Il Padrino", 10, "1950-10-10");
		
		Mockito.when(repo.findById(toUpdateDto.getId())).thenReturn(Optional.of(entity));
		
		Mockito.when(mapper.deserializza(toUpdateDto)).thenReturn(toUpdateEntity);
		
		Mockito.when(repo.save(toUpdateEntity)).thenReturn(toUpdateEntity);
		
		Mockito.when(mapper.serializza(toUpdateEntity)).thenReturn(toUpdateDto);
		
		FilmDTO updated = service.update(toUpdateDto);
		
		assertNotNull(updated);
		assertEquals(10, updated.getValutation());
	}
	
	@Test
	void tryingUpdateButNotFound() {
		FilmDTO toUpdateDto = new FilmDTO(1, "Il Padrino", 10, "1950-10-10");
		
		Mockito.when(repo.findById(toUpdateDto.getId())).thenReturn(Optional.empty());
		
		assertThrows(EntityNotFoundException.class, () -> service.update(toUpdateDto));
	}

}
