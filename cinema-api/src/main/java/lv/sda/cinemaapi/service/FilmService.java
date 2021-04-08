package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final static Integer ELEMENT_SIZE_PER_PAGE = 10;
    private final FilmRepository filmRepository;

    public Page<Film> getFilms(Integer offset) {
        return filmRepository.findAll(PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE));
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow();
    }
}
