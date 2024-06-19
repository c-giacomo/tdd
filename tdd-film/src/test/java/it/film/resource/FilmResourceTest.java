package it.film.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.film.dto.FilmDTO;
import it.film.resources.FilmResource;
import it.film.service.FilmService;

@WebMvcTest(controllers = FilmResource.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class FilmResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FilmService service;
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	void findAll() throws JsonProcessingException, Exception {
		List<FilmDTO> list = List.of(
				new FilmDTO(1, "La vita è bella", 8, "1990-01-01"),
				new FilmDTO(1, "Il pianista", 8, "1990-01-01")
			);
		
		Mockito.when(service.findAll()).thenReturn(list);
		
		ResultActions response = mockMvc.perform(get("/api/v1/films"));
		
		response.andExpectAll(status().isOk(),
				content().json(mapper.writeValueAsString(list)));
	}

}
