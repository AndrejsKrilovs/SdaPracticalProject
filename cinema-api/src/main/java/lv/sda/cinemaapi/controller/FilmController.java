package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/film.svc")
@CrossOrigin(origins="http://localhost:4200")
public class FilmController {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public FilmController(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    @GetMapping("/Films")
    public List<FilmDTO> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        return filmRepository.findAll(PageRequest.of(offset, 10))
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/Film({id})")
    public FilmDTO getOne(@PathVariable("id") Film film) {
        return filmMapper.toDTO(film);
    }
}
