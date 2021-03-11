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
import java.util.NoSuchElementException;
import java.util.Optional;
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
    public void getFilmByExistingIdTest() {
        Mockito.doReturn(Optional.of(filmTestData().get(0)))
                .when(repository).findById(1L);

        Film response = service.getFilmById(1L);
        Assert.assertEquals(filmTestData().get(0).getId(), response.getId());
        Assert.assertEquals(filmTestData().get(0).getTitle(), response.getTitle());
        Assert.assertEquals(filmTestData().get(0).getLength(), response.getLength());
        Assert.assertEquals(filmTestData().get(0).getPicturePath(), response.getPicturePath());
    }

    @Test
    public void getFilmByInvalidIdTest() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(0L);
        Assert.assertThrows(NoSuchElementException.class, () -> service.getFilmById(0L));
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