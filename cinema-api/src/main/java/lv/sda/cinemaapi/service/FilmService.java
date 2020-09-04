package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final static Integer ELEMENT_SIZE_PER_PAGE = 10;
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    public List<FilmDTO> getFilms(Integer offset) {
        return filmRepository.findAll(PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE))
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FilmDTO getFilm(Film film) {
        return filmMapper.toDTO(film);
    }
}
