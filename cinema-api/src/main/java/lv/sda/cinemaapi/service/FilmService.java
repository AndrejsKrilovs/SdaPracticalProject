package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final static Integer ELEMENT_SIZE_PER_PAGE = 10;
    private final FilmRepository filmRepository;

    public List<Film> getFilms(Integer offset) {
        return filmRepository.findAll(PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE))
                .stream()
                .collect(Collectors.toList());
    }

    public List<Film> getFilmsByTitle(String title, Integer offset) {
        if (StringUtils.isEmpty(title.trim())) {
            return filmRepository.findAll(PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE)).getContent();
        } else {
            return filmRepository.findAllByTitleContainingIgnoreCase(
                    title, PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE)
            );
        }
    }
}
