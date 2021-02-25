package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class FilmServiceTest {
    private final FilmRepository repository = Mockito.mock(FilmRepository.class);
    private final FilmService service = new FilmService(repository);

    @Test
    public void getNonEmptyListOfFilmsTest() {
        Film generatedFilm = Film.builder().build();
        Mockito.doReturn(new PageImpl<>(List.of(generatedFilm)))
                .when(repository)
                .findAll(PageRequest.of(0, 10));

        List<Film> filmList = service.getFilms(0);
        Assert.assertNotNull(filmList);
        Assert.assertFalse(filmList.isEmpty());
        Assert.assertEquals(1, filmList.size());
    }

    @Test
    public void getEmptyListOfFilmsTest() {
        Mockito.doReturn(new PageImpl<>(List.of()))
                .when(repository)
                .findAll(PageRequest.of(0, 10));

        List<Film> filmList = service.getFilms(0);
        Assert.assertNotNull(filmList);
        Assert.assertTrue(filmList.isEmpty());
    }

    @Test
    public void getFilmsByExistingTitleTest() {
        Film firstFilm = Film.builder().title("Test title 1").build();
        Film secondFilm = Film.builder().title("test title 2").build();
        Mockito.doReturn(List.of(firstFilm, secondFilm))
                .when(repository)
                .findAllByTitleContainingIgnoreCase("test", PageRequest.of(0, 10));

        List<Film> filteredFilms = service.getFilmsByTitle("test", 0);
        Assert.assertNotNull(filteredFilms);
        Assert.assertFalse(filteredFilms.isEmpty());
        Assert.assertEquals(2, filteredFilms.size());
    }

    @Test
    public void getFilmsByNotExistingTitleTest() {
        Film firstFilm = Film.builder().title("Test title 1").build();
        Film secondFilm = Film.builder().title("test title 2").build();
        Mockito.doReturn(List.of(firstFilm, secondFilm))
                .when(repository)
                .findAllByTitleContainingIgnoreCase("test", PageRequest.of(0, 10));

        List<Film> filteredFilms = service.getFilmsByTitle("non existing title", 0);
        Assert.assertNotNull(filteredFilms);
        Assert.assertTrue(filteredFilms.isEmpty());
    }
}