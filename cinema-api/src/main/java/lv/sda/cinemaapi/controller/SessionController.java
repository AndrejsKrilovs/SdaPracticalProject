package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.mapper.SessionMapper;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/session.svc")
@CrossOrigin(origins="http://localhost:4200")
public class SessionController {
    private final SessionRepository sessionRepository;
    private final SessionMapper mapper;

    public SessionController(SessionRepository sessionRepository, SessionMapper mapper) {
        this.sessionRepository = sessionRepository;
        this.mapper = mapper;
    }

    @GetMapping("/Sessions({film_id})")
    public List<SessionDTO> findSessionsByFilm(
            @PathVariable("film_id") Film film,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        return sessionRepository.findAllByFilm(film, PageRequest.of(offset, 7))
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
