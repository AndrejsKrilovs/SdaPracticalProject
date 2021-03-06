package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalTime;
import java.util.List;

public class FilmServiceTest {
    private final FilmRepository repository = Mockito.mock(FilmRepository.class);
    private final FilmService service = new FilmService(repository);

    @Test
    public void findNonEmptyFilmListTest() {
        Mockito.doReturn(new PageImpl<>(List.of(filmTestData())))
                .when(repository)
                .findAll(PageRequest.of(0, 10));

        List<Film> serviceResult = service.getFilms(0);
        Assert.assertNotNull(serviceResult);
        Assert.assertFalse(serviceResult.isEmpty());
    }

    @Test
    public void findEmptyFilmListTest() {
        Mockito.doReturn(new PageImpl<Film>(List.of()))
                .when(repository)
                .findAll(PageRequest.of(0, 10));

        List<Film> serviceResult = service.getFilms(0);
        Assert.assertNotNull(serviceResult);
        Assert.assertTrue(serviceResult.isEmpty());
    }

    @Test
    public void findFilmsByTitleTest() {
        Mockito.doReturn(List.of(filmTestData()))
                .when(repository)
                .findAllByTitleContainingIgnoreCase("test", PageRequest.of(0, 10));

        List<Film> serviceResult = service.getFilmsByTitle("test", 0);
        Assert.assertNotNull(serviceResult);
        Assert.assertFalse(serviceResult.isEmpty());
    }

    @Test
    public void findFilmsByEmptyTitleTest() {
        Mockito.doReturn(new PageImpl<>(List.of(filmTestData())))
                .when(repository)
                .findAll(PageRequest.of(0, 10));

        List<Film> serviceResult = service.getFilmsByTitle("", 0);
        Assert.assertNotNull(serviceResult);
        Assert.assertFalse(serviceResult.isEmpty());
    }

    @Test
    public void doNotFindFilmsByNotExistingTitleTest() {
        Mockito.doReturn(List.of(filmTestData()))
                .when(repository)
                .findAllByTitleContainingIgnoreCase("test", PageRequest.of(0, 10));

        List<Film> serviceResult = service.getFilmsByTitle("Some title", 0);
        Assert.assertNotNull(serviceResult);
        Assert.assertTrue(serviceResult.isEmpty());
    }

    private Film filmTestData() {
        Film testFilmData = new Film();
        testFilmData.setId(0L);
        testFilmData.setTitle("Test title");
        testFilmData.setPicturePath("/path/to/image");
        testFilmData.setLength(LocalTime.MIN);

        return testFilmData;
    }
}