package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmMapper {
    public Response<FilmDTO> generateResponse(Page<Film> filmPage) {
        boolean emptyFlag = filmPage.isEmpty();
        Metadata metadata = Metadata.builder()
                .pageNumber(emptyFlag ? 0 : filmPage.getPageable().getPageNumber())
                .totalPages(emptyFlag ? 0 : filmPage.getTotalPages() - 1)
                .totalElements(filmPage.getTotalElements())
                .offset(filmPage.getPageable().getOffset())
                .build();

        List<FilmDTO> contentData = emptyFlag ? List.of() :
                filmPage.stream()
                        .map(this::generateDTO)
                        .collect(Collectors.toList());

        return Response.<FilmDTO>builder()
                .entityList(contentData)
                .metadata(metadata)
                .build();
    }

    public Response<FilmDTO> generateSingleResponse(Film entity) {
        Metadata metadata = Metadata.builder()
                .totalElements(1L)
                .totalPages(0)
                .pageNumber(0)
                .offset(0L)
                .build();

        FilmDTO film = generateDTO(entity);
        return Response.<FilmDTO>builder()
                .entityList(List.of(film))
                .metadata(metadata)
                .build();
    }

    private FilmDTO generateDTO(Film entity) {
        return FilmDTO.builder()
                .picturePath(entity.getPicturePath())
                .length(entity.getLength())
                .title(entity.getTitle())
                .id(entity.getId())
                .build();
    }
}