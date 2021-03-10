package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class FilmServiceTest {
    private final FilmRepository repository = Mockito.mock(FilmRepository.class);
    private final FilmService service = new FilmService(repository);

    @Test
    public void getNonEmptyFilmListTest() {
        Mockito.doReturn(new PageImpl<>(filmTestData()))
                .when(repository)
                .findAll(PageRequest.of(0, 10));

        Page<Film> response = service.getFilms(0);
        Assert.assertFalse(response.getContent().isEmpty());
        Assert.assertEquals(1, response.getTotalPages());
        Assert.assertEquals(20, response.getTotalElements());
    }

    @Test
    public void getEmptyFilmListTest() {
        Mockito.doReturn(new PageImpl<Film>(List.of()))
                .when(repository)
                .findAll(PageRequest.of(0, 10));

        Page<Film> response = service.getFilms(0);
        Assert.assertTrue(response.getContent().isEmpty());
        Assert.assertEquals(1, response.getTotalPages());
        Assert.assertEquals(0, response.getTotalElements());
    }

    @Test
    public void findFilmsByTitleTest() {
        filmTestData();
//        Mockito.doReturn(List.of(filmTestData()))
//                .when(repository)
//                .findAllByTitleContainingIgnoreCase("test", PageRequest.of(0, 10));
//
//        List<Film> serviceResult = service.getFilmsByTitle("test", 0);
//        assertNotNull(serviceResult);
//        assertFalse(serviceResult.isEmpty());
    }

    @Test
    public void findFilmsByEmptyTitleTest() {
//        Mockito.doReturn(new PageImpl<>(List.of(filmTestData())))
//                .when(repository)
//                .findAll(PageRequest.of(0, 10));
//
//        List<Film> serviceResult = service.getFilmsByTitle("", 0);
//        assertNotNull(serviceResult);
//        assertFalse(serviceResult.isEmpty());
    }

    @Test
    public void doNotFindFilmsByNotExistingTitleTest() {
        Mockito.doReturn(List.of(filmTestData()))
                .when(repository)
                .findAllByTitleContainingIgnoreCase("test", PageRequest.of(0, 10));

        List<Film> serviceResult = service.getFilmsByTitle("Some title", 0);
        assertNotNull(serviceResult);
        assertTrue(serviceResult.isEmpty());
    }

    private List<Film> filmTestData() {
        return LongStream.rangeClosed(1, 20).mapToObj(item -> {
            Film testFilmData = new Film();
            testFilmData.setId(item);
            testFilmData.setTitle("Test title " + item);
            testFilmData.setPicturePath("/path/to/image");
            testFilmData.setLength(LocalTime.MIN);
            return testFilmData;
        }).collect(Collectors.toList());
    }
}