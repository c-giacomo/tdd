package it.crud.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import it.crud.model.Employee;
import it.crud.repository.EmployeeRepository;
import it.crud.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class TestServices {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks	// dice a spring che la dipendenza di service non è quella della classe ma quella mockkata qui sotto
	private EmployeeService employeeService;
	
	@Spy
	List<Employee> spyList = new LinkedList<>();
	
	@Mock
	List<Employee> mockList;
	
	@Captor
	ArgumentCaptor<Employee> captor;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(employeeRepository); // inizializzo un bean mocckato alla volta
		
//		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void test1() {
		Employee e = new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it");
		
		Employee e2 = new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it");
		e2.setId(100);
		
		Mockito.when(employeeRepository.save(e)).thenReturn(e2);
		
		Employee saved = employeeService.save(e);
		
		assertNotNull(saved);
		
		Mockito.verify(employeeRepository, Mockito.times(1)).save(Mockito.any(Employee.class));
	}
	
	@Test
	void test2() {
		Mockito.when(employeeRepository.save(null)).thenThrow(IllegalArgumentException.class);
		
		assertThrows(IllegalArgumentException.class, () -> employeeService.save(null));
//		assertNull(employeeService.save(null));
	}
	
	@Test
	void test3() throws Exception {
		List<Employee> result = List.of(
				new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it"),
				new Employee("Marco", "Rossi", "m.rossi@hotmail.it")
				);
				
		
		Mockito.when(employeeRepository.findAll()).thenReturn(result);
		
		result = employeeService.findAll();
		
		assertNotNull(result);
		assertEquals(2, result.size());
	}
	
	
	@Test
	void test4() throws Exception {
		Mockito.when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
		
		List<Employee> result = employeeService.findAll();
		
		assertEquals(0, result.size());
	}
	
	@Test
	void test5() {
		Employee emp = new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it");
		emp.setId(1);
		
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));
		
		Optional<Employee> result = employeeService.findById(1);
		
		assertTrue(result.isPresent());
		assertEquals(1 , result.get().getId());
	}
	
	@Test
	void test6() {
		Employee emp = new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it");
		
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.empty());
		
		Optional<Employee> result = employeeService.findById(1);
		
		assertEquals(result, Optional.empty());
	}
	
	@Test
	void test7() {
		Employee emp = new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it");
		emp.setId(1);
		
		Employee empMod = new Employee("Giacomo", "Chiavolotti", "caruso.paskoski@hotmail.it");
		empMod.setId(1);
		
		Mockito.when(employeeRepository.save(empMod)).thenReturn(empMod);
		
		Employee result = employeeService.save(empMod);
		
		assertEquals("caruso.paskoski@hotmail.it", result.getEmail());
		
	}
	
	@Test
	void test8() {
		Employee emp = new Employee();
//		Mockito.when(employeeRepository.save(emp)).thenThrow(EntityNotFoundException.class); non dovrebbe servire
		
		assertThrows(EntityNotFoundException.class, () -> employeeService.update(emp));
		
		Mockito.verify(employeeRepository, Mockito.times(0)).save(emp);
		
	}
	
	@Test
	void test9() {
		Employee emp = new Employee();
		emp.setId(1);
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));
		
		boolean result = employeeService.delete(emp);
		
		assertTrue(result);
		
		Mockito.verify(employeeRepository, Mockito.times(1)).findById(1);
		
	}
	
	@Test
	void test10() {
		Employee emp = new Employee("Giacomo", "Chiavolotti", "c.giacomo@hotmail.it");
		emp.setId(1);
		spyList.add(emp);
		
		Mockito.verify(spyList).add(captor.capture());
		
		assertEquals(1, spyList.size());
		assertEquals(emp, captor.getValue());
		
	}
}
