package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class SessionServiceTest {
    private final SessionRepository repository = Mockito.mock(SessionRepository.class);
    private final SessionService sessionService = new SessionService(repository);

    @Test
    public void findAllSessionsByExistingFilmTest() {
        Film filmMock1 = Film.builder().build();
        Session session1 = Session.builder().film(filmMock1).build();
        Session session2 = Session.builder().film(filmMock1).build();
        Mockito.doReturn(List.of(session1, session2))
                .when(repository)
                .findAllByFilm(filmMock1, PageRequest.of(0, 7));

        List<Session> sessionList = sessionService.findAllSessionsByFilm(filmMock1, 0);
        Assert.assertNotNull(sessionList);
        Assert.assertFalse(sessionList.isEmpty());
        Assert.assertEquals(2, sessionList.size());
    }

    @Test
    public void findAllSessionsByNonExistingFilmTest() {
        Film filmMock1 = Film.builder().id(1L).build();
        Session session1 = Session.builder().film(filmMock1).build();
        Session session2 = Session.builder().film(filmMock1).build();
        Film filmMock2 = Film.builder().id(2L).build();
        Mockito.doReturn(List.of(session1, session2))
                .when(repository)
                .findAllByFilm(filmMock1, PageRequest.of(0, 7));

        List<Session> sessionList = sessionService.findAllSessionsByFilm(filmMock2, 0);
        Assert.assertNotNull(sessionList);
        Assert.assertTrue(sessionList.isEmpty());
    }
}