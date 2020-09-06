package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/film.svc")
@CrossOrigin(origins="http://localhost:4200")
public class FilmController {

    private final FilmService filmService;
    private final FilmMapper filmMapper;

    public FilmController(FilmService filmService, FilmMapper filmMapper) {
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    @GetMapping("/Films")
    public ResponseEntity<List<FilmDTO>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        List<FilmDTO> resultList = filmService.getFilms(offset)
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
        return resultList.size() > 0 ?
                new ResponseEntity<>(resultList, HttpStatus.OK) :
                new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/Film({id})")
    public ResponseEntity<FilmDTO> getOne(@PathVariable("id") Film film) {
        return new ResponseEntity<>(filmMapper.toDTO(film), HttpStatus.FOUND);
    }
}
