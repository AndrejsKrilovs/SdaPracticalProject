package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final static Integer ELEMENT_SIZE_PER_PAGE = 7;
    private final SessionRepository sessionRepository;

    public List<Session> findAllSessionsByFilm(Film film, Integer offset) {
        return sessionRepository.findAllByFilm(film, PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE));
    }
}
