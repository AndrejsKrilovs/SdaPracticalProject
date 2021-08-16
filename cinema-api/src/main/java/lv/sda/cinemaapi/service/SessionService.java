package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.mapper.SessionMapper;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final static Integer ELEMENT_SIZE_PER_PAGE = 7;
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    public ResponseDTO<SessionDTO> getSessionsByFilm(Long filmId, Integer offset) {
        return sessionMapper.generateResponse(
                sessionRepository.findSessionsByFilm(filmId, PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE, Sort.by("dateTime")))
        );
    }
}
