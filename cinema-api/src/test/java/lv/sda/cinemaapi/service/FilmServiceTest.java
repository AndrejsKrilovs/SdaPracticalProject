package lv.sda.cinemaapi.service;

import lombok.extern.slf4j.Slf4j;
import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.mapper.FilmMapper;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Slf4j
public class FilmServiceTest {
    private final FilmMapper filmMapper = Mockito.mock(FilmMapper.class);
    private final FilmRepository filmRepository = Mockito.mock(FilmRepository.class);
    private final FilmService filmService = new FilmService(filmRepository, filmMapper);

    @Test
    public void testNonEmptyFilmList() {
       
    }

    @Test
    public void testEmptyFilmList() {

    }

    @Test
    public void findById() {
        Mockito.doReturn(Optional.of(generateFilm(1L)))
                .when(filmRepository)
                .findById(1L);

        Mockito.doReturn(generateResponse(1L))
                .when(filmMapper)
                .generateSingleResponse(generateFilm(1L));

        Optional<ResponseDTO<FilmDTO>> response = filmService.getFilmById(1L);
        Assert.assertTrue(response.isPresent());
        Assert.assertEquals(Long.valueOf(1), response.get().getMetadata().getTotalElements());
        Assert.assertEquals("title", response.get().getEntityList().get(0).getTitle());
        Assert.assertEquals("picture", response.get().getEntityList().get(0).getPicturePath());
        Assert.assertEquals(LocalTime.MIN, response.get().getEntityList().get(0).getLength());
    }

    @Test
    public void notFoundById() {
        Mockito.doReturn(Optional.of(generateFilm(0L)))
                .when(filmRepository)
                .findById(0L);

        Mockito.doReturn(generateResponse(0L))
                .when(filmMapper)
                .generateSingleResponse(generateFilm(0L));

        Optional<ResponseDTO<FilmDTO>> response = filmService.getFilmById(1L);
        Assert.assertFalse(response.isPresent());
    }

    private Film generateFilm(Long id) {
        Film film = new Film();
        film.setId(id);
        film.setTitle("title");
        film.setLength(LocalTime.MIN);
        film.setPicturePath("picture");
        return film;
    }

    private FilmDTO generateFilmDTO(Long id) {
        return FilmDTO.builder()
                .id(id)
                .title("title")
                .length(LocalTime.MIN)
                .picturePath("picture")
                .build();
    }

    private ResponseDTO<FilmDTO> generateResponse(Long id) {
        List<FilmDTO> filmList = LongStream.range(0, id)
                .mapToObj(this::generateFilmDTO)
                .collect(Collectors.toList());
        Metadata metadata = Metadata.builder()
                .totalPages(0)
                .pageNumber(0)
                .offset(0L)
                .totalElements((long) filmList.size())
                .build();
        return ResponseDTO.<FilmDTO>builder()
                .entityList(filmList)
                .metadata(metadata)
                .build();
    }
}