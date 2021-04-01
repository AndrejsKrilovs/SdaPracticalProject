package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionMapper {
    public ResponseDTO<SessionDTO> generateResponse(Page<Session> sessionPage) {
        boolean emptyFlag = sessionPage.isEmpty();
        Metadata metadata = Metadata.builder()
                .pageNumber(emptyFlag ? 0 : sessionPage.getPageable().getPageNumber())
                .totalPages((emptyFlag ? 0 : sessionPage.getTotalPages() - 1))
                .offset((sessionPage.getPageable().getOffset()))
                .totalElements(sessionPage.getTotalElements())
                .build();

        List<SessionDTO> contentData = emptyFlag ? List.of() :
                sessionPage.stream()
                        .map(this::generateSession)
                        .collect(Collectors.toList());

        return ResponseDTO.<SessionDTO>builder()
                .entityList(contentData)
                .metadata(metadata)
                .build();
    }

    public SessionDTO generateSession(Session session) {
        return SessionDTO.builder()
                .dateTime(session.getDateTime())
                .price(session.getPrice())
                .room(session.getRoom())
                .id(session.getId())
                .build();
    }
}
