package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmMapper {
    public Response<FilmDTO> generateResponse(Page<Film> filmPage) {
        boolean emptyFlag = filmPage.isEmpty();
        Metadata metadata = new Metadata();
        metadata.setTotalPages(emptyFlag ? 0 : filmPage.getTotalPages() - 1);
        metadata.setTotalElements(filmPage.getTotalElements());
        metadata.setOffset(filmPage.getPageable().getOffset());
        metadata.setPageNumber(emptyFlag ? 0 : filmPage.getPageable().getPageNumber());

        Response<FilmDTO> response = new Response<>();
        List<FilmDTO> contentData = emptyFlag ? List.of() :
                filmPage.stream().map(this::toDTO).collect(Collectors.toList());
        response.setEntityList(contentData);
        response.setMetadata(metadata);

        return response;
    }

    public Response<FilmDTO> generateSingleResponse(Film film) {
        Metadata metadata = new Metadata();
        metadata.setTotalPages(0);
        metadata.setTotalElements(1L);
        metadata.setOffset(0L);
        metadata.setPageNumber(0);

        Response<FilmDTO> response = new Response<>();
        response.setEntityList(List.of(toDTO(film)));
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
