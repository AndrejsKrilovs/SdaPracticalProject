package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.entity.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SessionMapperTest {

    @Autowired
    private SessionMapper sessionMapper;

    @Test
    public void fromDTO() {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(47L);
        sessionDTO.setDateTime("25.09.2020 01:55");
        sessionDTO.setRoom(2);
        sessionDTO.setPrice(BigDecimal.valueOf(3.98));
        sessionDTO.setFilmId(12L);

        Session session = sessionMapper.fromDTO(sessionDTO);

        assertEquals(47L, session.getId());
        assertEquals(
                LocalDateTime.parse("25.09.2020 01:55", DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm")),
                session.getDateTime()
        );
        assertEquals(Room.TWO, session.getRoom());
        assertEquals(BigDecimal.valueOf(3.98), session.getPrice());
        assertEquals(12L, session.getFilm().getId());
    }

    @Test
    public void toDTO() {
        Session session = new Session();
        session.setId(47L);
        session.setDateTime(LocalDateTime.parse("25.09.2020 01:55",
                DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm")));
        session.setRoom(Room.TWO);
        session.setPrice(BigDecimal.valueOf(3.98));

        Film film = new Film();
        film.setId(12L);
        session.setFilm(film);

        SessionDTO sessionDTO = sessionMapper.toDTO(session);

        assertEquals(47L, sessionDTO.getId());
        assertEquals("25.09.2020 01:55", sessionDTO.getDateTime());
        assertEquals(2, sessionDTO.getRoom());
        assertEquals(BigDecimal.valueOf(3.98), sessionDTO.getPrice());
        assertEquals(12L, sessionDTO.getFilmId());
    }
}