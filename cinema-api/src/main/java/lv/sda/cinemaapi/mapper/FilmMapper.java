package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.FilmDTO;
import lv.sda.cinemaapi.entity.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper extends AbstractMapper<FilmDTO, Film>{

    @Override
    protected FilmDTO generateDTO(Film entity) {
        return FilmDTO.builder()
                .picturePath(entity.getPicturePath())
                .length(entity.getLength())
                .title(entity.getTitle())
                .id(entity.getId())
                .build();
    }

    @Override
    protected Film generateEntity(FilmDTO dto) {
        Film result = new Film();
        result.setId(dto.getId());
        result.setLength(dto.getLength());
        result.setTitle(dto.getTitle());
        result.setPicturePath(dto.getPicturePath());
        return result;
    }
}