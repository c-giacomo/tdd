package it.film.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "film")
@NoArgsConstructor
public class Film implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private int valutation;
	private String releaseDate;
	
	
	public Film(String title, int valutation, String releaseDate) {
		super();
		this.title = title;
		this.valutation = valutation;
		this.releaseDate = releaseDate;
	}
	
	public Film(Integer id, String title, int valutation, String releaseDate) {
		super();
		this.id = id;
		this.title = title;
		this.valutation = valutation;
		this.releaseDate = releaseDate;
	}
	
	
	
	

}
