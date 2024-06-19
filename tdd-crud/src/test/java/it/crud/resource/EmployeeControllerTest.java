package it.crud.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.crud.model.Employee;
import it.crud.resources.EmployeeController;
import it.crud.service.EmployeeService;

@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@Test
	void test1() throws JsonProcessingException, Exception {
		List<Employee> list = List.of(
				new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it"),
				new Employee("Marco", "Rossi", "m.rossi@hotmail.it")
			);
		
		Mockito.when(service.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/api/employee"))
			.andExpectAll(
					status().isOk(),
					content().json(mapper.writeValueAsString(list))
				);
	}
	
	@Test
	void test2() throws JsonProcessingException, Exception {
		Mockito.when(service.findAll()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/api/employee"))
			.andExpectAll(
					status().is5xxServerError()
				);
	}
	
	@Test
	void test3() throws JsonProcessingException, Exception {
		Employee e = new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it");
		e.setId(1);
		Mockito.when(service.save(Mockito.any(Employee.class))).thenReturn(e);
		
		ResultActions response = mockMvc.perform(post("/api/employee")
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8")
					.content(mapper.writeValueAsString(e))
				);
		response.andExpectAll(status().isOk(),
//				jsonPath("$.name", CoreMatchers.is(e.getName())));
				jsonPath("$.name", CoreMatchers.is("Giacomo")));
	}
	
	@Test
	void test4() throws JsonProcessingException, Exception {
		Optional<Employee> e = Optional.ofNullable(new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it"));
		e.get().setId(1);
		Mockito.when(service.findById(1)).thenReturn(e);
		
		ResultActions response = mockMvc.perform(get("/api/employee/1")
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8")
					.content(mapper.writeValueAsString(e))
				);
		response.andExpectAll(status().isOk(),
//				jsonPath("$.name", CoreMatchers.is(e.getName())));
				jsonPath("$.name", CoreMatchers.is("Giacomo")));
	}
	
	@Test
	void test5() throws JsonProcessingException, Exception {
		Mockito.when(service.findById(3)).thenReturn(null);
		
		mockMvc.perform(get("/api/employee/3"))
				.andExpect(
						status().isBadRequest()
					);
	}
	
	@Test
	void test6() throws JsonProcessingException, Exception {
		Employee e = new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it");
		e.setId(1);
		
		Employee mod = new Employee("Arturo", "Chiavolotti", "bovaro@hotmail.it");
		mod.setId(1);
		
		Mockito.when(service.update(Mockito.any(Employee.class))).thenReturn(mod);
		
		ResultActions response = mockMvc.perform(put("/api/employee")
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8")
					.content(mapper.writeValueAsString(mod))
				);
		response.andExpectAll(status().isOk(),
//				jsonPath("$.name", CoreMatchers.is(e.getName())));
				jsonPath("$.name", CoreMatchers.is("Arturo")));
	}
	
	@Test
	void test7() throws JsonProcessingException, Exception {
		Mockito.when(service.delete(Mockito.any(Employee.class))).thenReturn(true);
		
		ResultActions response = mockMvc.perform(delete("/api/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(mapper.writeValueAsString(new Employee()))
			);
		response.andExpectAll(
				status().isOk(),
				content().string("true")
			);
	}

}
