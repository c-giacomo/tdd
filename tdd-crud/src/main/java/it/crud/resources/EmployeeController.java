package it.crud.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.crud.model.Employee;
import it.crud.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> result = employeeService.findAll();
		if (!result.isEmpty())
			return new ResponseEntity<>(result, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PostMapping
	public ResponseEntity<Employee> save(@RequestBody Employee e) {
		return new ResponseEntity<>(employeeService.save(e), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Employee> update(@RequestBody Employee e) {
		Employee emp = employeeService.update(e);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findById(@PathVariable("id") Integer id) {
		Optional<Employee> result = employeeService.findById(1);
		if (result.isPresent())
			return new ResponseEntity<>(result.get(), HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody Employee e) {
		boolean result = employeeService.delete(e);
		if (result) return new ResponseEntity<>("true", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
