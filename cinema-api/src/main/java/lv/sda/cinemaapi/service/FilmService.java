package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final static Integer ELEMENT_SIZE_PER_PAGE = 10;
    private final FilmRepository filmRepository;

    public Page<Film> getFilms(Integer offset) {
        return filmRepository.findAll(PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE));
    }

    public List<Film> getFilmsByTitle(String title, Integer offset) {
        if (title.isBlank()) {
            return filmRepository.findAll(PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE)).getContent();
        } else {
            return filmRepository.findAllByTitleContainingIgnoreCase(title, PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE)
            );
        }
    }
}
