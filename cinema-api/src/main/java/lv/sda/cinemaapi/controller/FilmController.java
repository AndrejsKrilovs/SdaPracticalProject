package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.FilmResponse;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/film.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {
    private final FilmService filmService;
    private final FilmMapper filmMapper;

    @GetMapping(path = "/Films")
    public ResponseEntity<FilmResponse> findFilms(
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        FilmResponse response = filmMapper.generateResponse(filmService.getFilms(offset));
        return !response.getEntityList().isEmpty() ?
                new ResponseEntity<>(response, HttpStatus.OK) :
                new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/Film({id})")
    public ResponseEntity<FilmResponse> findFilmById(@PathVariable Long id) {
        return new ResponseEntity<>(filmMapper.generateSingleResponse(filmService.getFilmById(id)), HttpStatus.FOUND);
    }
//
//    @GetMapping("/Films$filter")
//    public ResponseEntity<List<FilmDTO>> findAllByTitle(
//            @RequestParam(value = "title", required = false, defaultValue = "") String title,
//            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
//    ) {
//        List<FilmDTO> resultList = filmService.getFilmsByTitle(title, offset)
//                .stream()
//                .map(filmMapper::toDTO)
//                .collect(Collectors.toList());
//
//        return resultList.size() > 0 ?
//                new ResponseEntity<>(resultList, HttpStatus.OK) :
//                new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
//    }
}
