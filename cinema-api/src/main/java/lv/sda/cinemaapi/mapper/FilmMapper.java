package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {
    public FilmDTO toDTO(Film film) {
        FilmDTO result  = new FilmDTO();
        result.setId(film.getId());
        result.setTitle(film.getTitle());
        result.setPicturePath(film.getPicturePath());
        result.setLength(film.getLength());
        return result;
    }
}
