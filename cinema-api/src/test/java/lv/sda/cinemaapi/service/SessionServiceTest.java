package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SessionServiceTest {
    private final SessionRepository repository = Mockito.mock(SessionRepository.class);
    private final SessionService sessionService = new SessionService(repository);

    @Test
    public void getSessionsByExistingFilmTest() {
        Mockito.doReturn(new PageImpl<>(sessionTestData()))
                .when(repository)
                .findAllByFilm(0L, PageRequest.of(0, 7));

        Page<Session> response = sessionService.getSessionsByFilm(0L, 0);
        Assert.assertFalse(response.getContent().isEmpty());
        Assert.assertEquals(1, response.getTotalPages());
        Assert.assertEquals(20, response.getTotalElements());
    }

    @Test
    public void getSessionsByInvalidFilmTest() {
        Mockito.doReturn(new PageImpl<>(sessionTestData()))
                .when(repository)
                .findAllByFilm(0L, PageRequest.of(0, 7));

        Page<Session> response = sessionService.getSessionsByFilm(Long.MIN_VALUE, 0);
        Assert.assertNull(response);
    }

    private Film testFilmData() {
        Film testFilmData = new Film();
        testFilmData.setId(0L);
        testFilmData.setTitle("Some title");
        testFilmData.setPicturePath("some/path");
        testFilmData.setLength(LocalTime.MIN);
        return testFilmData;
    }

    private List<Session> sessionTestData() {
        return LongStream.rangeClosed(1, 20).mapToObj(item -> {
            Session testSessionData = new Session();
            testSessionData.setId(item);
            testSessionData.setFilm(testFilmData());
            testSessionData.setDateTime(LocalDateTime.MIN);
            testSessionData.setRoom(Room.ZERO);
            testSessionData.setPrice(BigDecimal.ZERO);
            return testSessionData;
        }).collect(Collectors.toList());
    }
}