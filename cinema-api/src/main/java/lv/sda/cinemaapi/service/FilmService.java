package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final static Integer ELEMENT_SIZE_PER_PAGE = 10;
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getFilms(Integer offset) {
        return filmRepository.findAll(PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE))
                .stream()
                .collect(Collectors.toList());
    }
}
