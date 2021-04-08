package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final static Integer ELEMENT_SIZE_PER_PAGE = 7;
    private final SessionRepository sessionRepository;

    public Page<Session> getSessionsByFilm(Long filmId, Integer offset) {
        return sessionRepository.findSessionsByFilm(filmId,
                PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE, Sort.by("id.session.dateTime")));
    }
}
