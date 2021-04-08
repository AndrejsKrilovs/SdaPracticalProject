package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.dto.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class AbstractMapper<T, E> {
    public ResponseDTO<T> generateSingleResponse(E entity) {
        Metadata metadata = Metadata.builder()
                .totalElements(1L)
                .totalPages(0)
                .pageNumber(0)
                .offset(0L)
                .build();
        return ResponseDTO.<T>builder()
                .entityList(List.of(generateDTO(entity)))
                .build();
    }

    public ResponseDTO<T> generateResponse(Page<E> data) {
        boolean emptyFlag = data.isEmpty();
        Metadata metadata = Metadata.builder()
                .pageNumber(emptyFlag ? 0 : data.getPageable().getPageNumber())
                .totalPages(emptyFlag ? 0 : data.getTotalPages() - 1)
                .totalElements(data.getTotalElements())
                .offset(data.getPageable().getOffset())
                .build();

        List<T> contentData = emptyFlag ? List.of() :
                data.stream()
                        .map(this::generateDTO)
                        .collect(Collectors.toList());

        return ResponseDTO.<T>builder()
                .entityList(contentData)
                .metadata(metadata)
                .build();
    }

    public abstract T generateDTO(E entity);
}
