package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final static Integer ELEMENT_SIZE_PER_PAGE = 12;
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public ResponseDTO<FilmDTO> getFilms(Integer offset) {
        return filmMapper.generateResponse(filmRepository.findAll(PageRequest.of(offset, ELEMENT_SIZE_PER_PAGE)));
    }

    public Optional<ResponseDTO<FilmDTO>> getFilmById(Long id) {
        return filmRepository.findById(id).map(filmMapper::generateSingleResponse);
    }
}
