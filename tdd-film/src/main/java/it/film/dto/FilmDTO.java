package it.film.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmDTO {
	
	private Integer id;
	private String title;
	private int valutation;
	private String releaseDate;
	
	public FilmDTO(String title, int valutation, String releaseDate) {
		super();
		this.title = title;
		this.valutation = valutation;
		this.releaseDate = releaseDate;
	}
	
	public FilmDTO(Integer id, String title, int valutation, String releaseDate) {
		super();
		this.id = id;
		this.title = title;
		this.valutation = valutation;
		this.releaseDate = releaseDate;
	}

}
