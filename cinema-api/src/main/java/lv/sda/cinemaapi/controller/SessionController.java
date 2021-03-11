package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.mapper.SessionMapper;
import lv.sda.cinemaapi.service.SessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/session.svc")
@CrossOrigin(origins="http://localhost:4200")
public class SessionController {
    private final SessionService sessionService;
    private final SessionMapper sessionMapper;

    @GetMapping(path = "/Sessions({film_id})")
    public Response<SessionDTO> findSessionsByFilm(
            @PathVariable(value = "film_id") Long filmId,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        return sessionMapper.generateResponse(sessionService.getSessionsByFilm(filmId, offset));
    }
}
