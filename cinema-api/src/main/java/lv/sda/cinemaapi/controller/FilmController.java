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

/**
 * @AleksandrKozirev
 * 1. Make an AbstractController and extend from it.
 * 2. In findAll() method pass limit and offset arguments. Should be findAll(int offset, limit).
 *  Where {offset} - parameter from which page start, {limit} - parameter how many records on page to display
 *  Use {@link PageRequest} class documentation
 */
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
    public List<FilmDTO> findAll() {
        return filmRepository.findAll(PageRequest.of(1, 10))
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/Film({id})")
    public FilmDTO getOne(@PathVariable("id") Film film) {
        return filmMapper.toDTO(film);
    }

    @PostMapping("/Film")
    public FilmDTO add(@RequestBody FilmDTO filmDTO) {
        Film entity = filmMapper.fromDTO(filmDTO);
        Film newEntity = filmRepository.save(entity);
        return filmMapper.toDTO(newEntity);
    }

    @PutMapping("/Film({id})")
    public FilmDTO update(@PathVariable("id") Film filmFromDB, @RequestBody FilmDTO filmDTO) {
        Film filmToUpdate = filmMapper.fromDTO(filmDTO);
        BeanUtils.copyProperties(filmToUpdate, filmFromDB, "id");
        Film result = filmRepository.save(filmFromDB);
        return filmMapper.toDTO(result);
    }

    @DeleteMapping("/Film({id})")
    public void delete(@PathVariable("id") Film film) {
        filmRepository.delete(film);
    }
}
