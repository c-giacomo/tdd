package it.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.crud.model.Employee;
import it.crud.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;

	public Employee save(Employee e) throws IllegalArgumentException {
		return employeeRepository.save(e);
	}
	
	public Employee update(Employee e) {
		Optional<Employee> emp = employeeRepository.findById(e.getId());
		if (!emp.isPresent()) throw new EntityNotFoundException();
		return employeeRepository.save(e);
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> findById(Integer i) {
		return employeeRepository.findById(i);
	}

	public boolean delete(Employee emp) {
		Optional<Employee> employe = employeeRepository.findById(emp.getId());
		if (!employe.isPresent()) throw new EntityNotFoundException();
		employeeRepository.delete(emp);
		return true;
	}

}
