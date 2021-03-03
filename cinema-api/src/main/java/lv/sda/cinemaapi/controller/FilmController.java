package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/film.svc")
@CrossOrigin(origins="http://localhost:4200")
public class FilmController {
    private final FilmService filmService;
    private final FilmMapper filmMapper;

    @GetMapping(path = "/Films")
    public ResponseEntity<List<FilmDTO>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        List<FilmDTO> resultList = filmService.getFilms(offset)
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
        return resultList.size() > 0 ?
                new ResponseEntity<>(resultList, HttpStatus.OK) :
                new ResponseEntity<>(List.of(), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/Film({id})")
    public ResponseEntity<FilmDTO> getOne(@PathVariable("id") Film film) {
        return new ResponseEntity<>(filmMapper.toDTO(film), HttpStatus.OK);
    }

    @GetMapping(path = "/Films$filter")
    public ResponseEntity<List<FilmDTO>> findAllByTitle(
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        List<FilmDTO> resultList = filmService.getFilmsByTitle(title, offset)
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());

        return resultList.size() > 0 ?
                new ResponseEntity<>(resultList, HttpStatus.OK) :
                new ResponseEntity<>(List.of(), HttpStatus.NO_CONTENT);
    }
}
