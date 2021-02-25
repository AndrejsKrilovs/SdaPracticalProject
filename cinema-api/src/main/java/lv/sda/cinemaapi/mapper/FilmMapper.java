package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class FilmMapper {
    public Film fromDTO(FilmDTO filmDTO) {
        Film result = Film.builder().build();
        result.setId(filmDTO.getId());
        result.setTitle(filmDTO.getTitle());
        result.setPicturePath(filmDTO.getPicturePath());
        result.setLength(LocalTime.parse(filmDTO.getLength()));
        return result;
    }

    public FilmDTO toDTO(Film film) {
        FilmDTO result  = new FilmDTO();
        result.setId(film.getId());
        result.setTitle(film.getTitle());
        result.setPicturePath(film.getPicturePath());
        result.setLength(film.getLength().toString());
        return result;
    }
}
