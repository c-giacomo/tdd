package it.film.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.film.dto.FilmDTO;
import it.film.model.Film;

@Mapper(componentModel = "spring")
public interface FilmMapper {
	
	@BeanMapping(ignoreByDefault = true)
	@Mapping(target = "title", source = "title")
	FilmDTO serializza(Film film);
	
	Film deserializza(FilmDTO filmDto);
	
	List<FilmDTO> serializzaStream(List<Film> film);

}
