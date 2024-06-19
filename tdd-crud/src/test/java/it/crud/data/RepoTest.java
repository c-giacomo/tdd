package it.crud.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import it.crud.model.Employee;
import it.crud.repository.EmployeeRepository;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class RepoTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	void test() {
		Employee e = new Employee();
		e.setName("Giacomo");
		e.setSurname("Chiavolotti");
		e.setEmail("c.giacomo@hotmail.it");
		
		e = employeeRepository.save(e);
		List<Employee> r = employeeRepository.findAll();
		
		assertEquals(1, e.getId());
		assertEquals(1, r.size());
	}

}
