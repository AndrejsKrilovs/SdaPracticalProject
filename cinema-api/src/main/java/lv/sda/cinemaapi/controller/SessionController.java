package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.service.SessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/session.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class SessionController {
    private final SessionService sessionService;

    @GetMapping(path = "/Sessions")
    public ResponseDTO<SessionDTO> findSessionsByFilm(
            @RequestParam(value = "film_id") Long filmId,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        return sessionService.getSessionsByFilm(filmId, offset);
    }
}
