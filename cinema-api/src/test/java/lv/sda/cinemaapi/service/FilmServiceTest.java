package lv.sda.cinemaapi.service;

import com.google.common.collect.ImmutableList;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class FilmServiceTest {

    private final FilmRepository repository = Mockito.mock(FilmRepository.class);
    private final FilmService service = new FilmService(repository);

    @Before
    public void setUp() throws Exception {
        Film film1 = new Film();
        film1.setId(1L);
        film1.setLength(LocalTime.of(2, 0));
        film1.setTitle("Alive");
        film1.setPicturePath("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTV7mO2pcvBJjnBst2TsIsxkNdpOZVkTR2W9umZBSXuK7xMIvFW");

        Film film2 = new Film();
        film1.setId(2L);
        film1.setLength(LocalTime.of(1, 43));
        film1.setTitle("Fantasy Island");
        film1.setPicturePath("https://images-na.ssl-images-amazon.com/images/I/912UPJuKy8L._RI_.jpg");

        doReturn(new PageImpl<>(ImmutableList.of(film1, film2))).when(repository)
                .findAll(PageRequest.of(0, 10));
        doReturn(ImmutableList.of(film2)).when(repository)
                .findAllByTitleContainingIgnoreCase("island", PageRequest.of(0, 10));
    }

    @Test
    public void getFilms() {
        List<Film> getFilms = service.getFilms(0);
        assertEquals(2, getFilms.size());
    }

    @Test
    public void getFilmsByTitle() {
        List<Film> filteredFilms = service.getFilmsByTitle("island", 0);
        assertEquals(1, filteredFilms.size());
    }

    @Test
    public void getFilmsByEmptyTitle() {
        List<Film> filteredFilms = service.getFilmsByTitle("", 0);
        assertEquals(0, filteredFilms.size());
    }
}