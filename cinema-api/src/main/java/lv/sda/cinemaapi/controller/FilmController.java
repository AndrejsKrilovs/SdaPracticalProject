package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.service.FilmService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/film.svc")
public class FilmController {
    private final FilmService filmService;
    private final FilmMapper filmMapper;

    @GetMapping(path = "/Films")
    public Response<FilmDTO> findFilms(
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        return filmMapper.generateResponse(filmService.getFilms(offset));
    }

    @GetMapping(path = "/Film({id})")
    public Response<FilmDTO> findFilmById(@PathVariable Long id) {
        return filmMapper.generateSingleResponse(filmService.getFilmById(id));
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
