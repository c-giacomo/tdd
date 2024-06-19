package it.testdate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.testdate.repository.EntitaRepository;

@SpringBootApplication
public class TestDateApplication implements CommandLineRunner {
	
	
	@Autowired
	private EntitaRepository repo;
	
	@Value("#{T(java.util.Arrays).asList('${visibility.state:}')}") 
	private List<String> ddParams;

	public static void main(String[] args) {
		SpringApplication.run(TestDateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(ddParams.size());
//		Entita e = repo.findById(1).get();
//		Date data = e.getData2();
//		
//		System.out.println(new Date().after(data));
		
//		Date data = new Date();
//		
//		Specification spec = Specification.where(TestSpecification.findByDataGreaterOrEqual(data));
//		
//		List<Entita> result = repo.findAll(spec);
//		
//		System.out.println(result.size());
		
		
	}

}
