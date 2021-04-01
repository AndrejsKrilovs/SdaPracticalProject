package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.service.FilmService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/film.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {
    private final FilmService filmService;
    private final FilmMapper filmMapper;

    @GetMapping(path = "/Films")
    public ResponseDTO<FilmDTO> findFilms(
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        return filmMapper.generateResponse(filmService.getFilms(offset));
    }

    @GetMapping(path = "/Film({id})")
    public ResponseDTO<FilmDTO> findFilmById(@PathVariable Long id) {
        return filmMapper.generateSingleResponse(filmService.getFilmById(id));
    }
}
