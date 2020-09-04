package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.mapper.SessionMapper;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final static Integer ELEMENT_SIZE_PER_PAGE = 7;
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAllSessionsByFilm(Film film, Integer offset) {
        return new ArrayList<>(sessionRepository.findAllByFilm(film, PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE)));
    }
}
