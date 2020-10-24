package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.entity.Session;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class SessionMapperTest {

    private final SessionMapper sessionMapper = new SessionMapper();

    @Test
    public void fromDTO() {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(47L);
        sessionDTO.setDateTime("25.09.2020 01:55");
        sessionDTO.setRoom(2);
        sessionDTO.setPrice(BigDecimal.valueOf(3.98));
        sessionDTO.setFilmId(12L);

        Session session = sessionMapper.fromDTO(sessionDTO);

        assertEquals(java.util.Optional.of(47L), Optional.ofNullable(session.getId()));
        assertEquals(
                LocalDateTime.parse("25.09.2020 01:55", DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm")),
                session.getDateTime()
        );
        assertEquals(Room.TWO, session.getRoom());
        assertEquals(BigDecimal.valueOf(3.98), session.getPrice());
        assertEquals(Optional.of(12L), Optional.ofNullable(session.getFilm().getId()));
    }

    @Test
    public void toDTO() {
        Session session = new Session();
        session.setId(47L);
        session.setDateTime(LocalDateTime.parse("25.09.2020 01:55", DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm")));
        session.setRoom(Room.TWO);
        session.setPrice(BigDecimal.valueOf(3.98));

        Film film = new Film();
        film.setId(12L);
        session.setFilm(film);

        SessionDTO sessionDTO = sessionMapper.toDTO(session);

        assertEquals(Optional.of(47L), Optional.ofNullable(sessionDTO.getId()));
        assertEquals("25.09.2020 01:55", sessionDTO.getDateTime());
        assertEquals(Optional.of(2), Optional.ofNullable(sessionDTO.getRoom()));
        assertEquals(BigDecimal.valueOf(3.98), sessionDTO.getPrice());
        assertEquals(Optional.of(12L), Optional.ofNullable(sessionDTO.getFilmId()));
    }
}