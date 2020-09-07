package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FilmMapperTest {

    private final FilmMapper filmMapper = new FilmMapper();

    @Test
    void fromDTO() {
        FilmDTO filmDTO  = new FilmDTO();
        filmDTO.setId(100L);
        filmDTO.setTitle("Title 100");
        filmDTO.setPicturePath("https://");
        filmDTO.setLength("01:55");

        Film film = filmMapper.fromDTO(filmDTO);

        assertEquals(100L, film.getId());
        assertEquals("Title 100", film.getTitle());
        assertEquals("https://", film.getPicturePath());
        assertEquals(LocalTime.parse("01:55"), LocalTime.parse(filmDTO.getLength()));
    }

    @Test
    void toDTO() {
        Film film = new Film();
        film.setId(100L);
        film.setTitle("Title 100");
        film.setPicturePath("https://");
        film.setLength(LocalTime.parse("01:55"));

        FilmDTO filmDTO = filmMapper.toDTO(film);

        assertEquals(100L, filmDTO.getId());
        assertEquals("Title 100", filmDTO.getTitle());
        assertEquals("https://", filmDTO.getPicturePath());
        assertEquals("01:55", filmDTO.getLength());
    }
}