package it.testdate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.testdate.model.Entita;

public interface EntitaRepository extends JpaRepository<Entita, Integer>, JpaSpecificationExecutor<Entita> {
	
	

}
