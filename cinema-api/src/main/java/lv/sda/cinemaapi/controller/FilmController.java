package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film.svc")
@CrossOrigin(origins="http://localhost:4200")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/Films")
    public List<FilmDTO> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0")
                                             Integer offset) {
        return filmService.getFilms(offset);
    }

    @GetMapping("/Film({id})")
    public FilmDTO getOne(@PathVariable("id") Film film) {
        return filmService.getFilm(film);
    }
}
