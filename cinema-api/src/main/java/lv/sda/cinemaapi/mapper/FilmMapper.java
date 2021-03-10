package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.dto.FilmResponse;
import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmMapper {
    public FilmResponse generateResponse(Page<Film> filmPage) {
        boolean emptyFlag = filmPage.isEmpty();
        Metadata metadata = new Metadata();
        metadata.setTotalPages(emptyFlag ? 0 : filmPage.getTotalPages() - 1);
        metadata.setTotalElements(filmPage.getTotalElements());
        metadata.setOffset(filmPage.getPageable().getOffset());
        metadata.setPageNumber(emptyFlag ? 0 : filmPage.getPageable().getPageNumber());

        FilmResponse response = new FilmResponse();
        List<FilmDTO> contentData = emptyFlag ? List.of() :
                filmPage.stream().map(this::toDTO).collect(Collectors.toList());
        response.setEntityList(contentData);
        response.setMetadata(metadata);

        return response;
    }

    private FilmDTO toDTO(Film film) {
        FilmDTO result = new FilmDTO();
        result.setId(film.getId());
        result.setTitle(film.getTitle());
        result.setPicturePath(film.getPicturePath());
        result.setLength(film.getLength());
        return result;
    }
}
