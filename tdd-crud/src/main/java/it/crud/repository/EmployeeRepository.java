package it.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.crud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public Employee findByEmail(String email);

}
