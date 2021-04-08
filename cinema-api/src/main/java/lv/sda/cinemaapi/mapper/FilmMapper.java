package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper extends AbstractMapper<FilmDTO, Film>{

    @Override
    public FilmDTO generateDTO(Film entity) {
        return FilmDTO.builder()
                .picturePath(entity.getPicturePath())
                .length(entity.getLength())
                .title(entity.getTitle())
                .id(entity.getId())
                .build();
    }
}