package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.entity.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SessionMapper {
    public Session fromDTO(SessionDTO sessionDTO) {
        Session result = Session.builder().build();
        result.setId(sessionDTO.getId());
        result.setDateTime(LocalDateTime.parse(sessionDTO.getDateTime(), DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm")));
        result.setRoom(Room.values()[sessionDTO.getRoom()]);
        result.setPrice(sessionDTO.getPrice());

        Film film = Film.builder().build();
        film.setId(sessionDTO.getFilmId());
        result.setFilm(film);
        return result;
    }

    public SessionDTO toDTO(Session session) {
        SessionDTO result = new SessionDTO();
        result.setId(session.getId());
        result.setDateTime(session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm")));
        result.setRoom(session.getRoom().ordinal());
        result.setPrice(session.getPrice());
        result.setFilmId(session.getFilm().getId());
        return result;
    }
}
