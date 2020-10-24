package lv.sda.cinemaapi.service;

import com.google.common.collect.ImmutableList;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class SessionServiceTest {

    private final SessionRepository repository = Mockito.mock(SessionRepository.class);
    private final SessionService sessionService = new SessionService(repository);
    private final Film film = Mockito.mock(Film.class);

    @Before
    public void setUp() throws Exception {
        Session session1 = new Session();
        session1.setId(1L);
        session1.setRoom(Room.ONE);
        session1.setDateTime(LocalDateTime.now());
        session1.setPrice(BigDecimal.ONE);
        session1.setFilm(film);

        Session session2 = new Session();
        session2.setId(2L);
        session2.setRoom(Room.ONE);
        session2.setDateTime(LocalDateTime.now());
        session2.setPrice(BigDecimal.ONE);
        session2.setFilm(film);

        doReturn(ImmutableList.of(session1, session2)).when(repository)
                .findAllByFilm(film, PageRequest.of(0, 7));
    }

    @Test
    public void findAllSessionsByFilm() {
        List<Session> sessionList = sessionService.findAllSessionsByFilm(film, 0);
        assertEquals(2, sessionList.size());
    }
}