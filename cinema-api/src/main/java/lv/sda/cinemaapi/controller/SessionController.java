package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.mapper.SessionMapper;
import lv.sda.cinemaapi.service.SessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/session.svc")
public class SessionController {
    private final SessionService sessionService;
    private final SessionMapper sessionMapper;

    @GetMapping(path = "/Sessions")
    public Response<SessionDTO> findSessionsByFilm(
            @RequestParam(value = "film_id") Long filmId,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        return sessionMapper.generateResponse(sessionService.getSessionsByFilm(filmId, offset));
    }
}
