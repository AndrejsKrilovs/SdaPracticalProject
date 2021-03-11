package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionMapper {
    public Response<SessionDTO> generateResponse(Page<Session> sessionPage) {
        boolean emptyFlag = sessionPage.isEmpty();
        Metadata metadata = new Metadata();
        metadata.setTotalPages(emptyFlag ? 0 : sessionPage.getTotalPages() - 1);
        metadata.setTotalElements(sessionPage.getTotalElements());
        metadata.setOffset(sessionPage.getPageable().getOffset());
        metadata.setPageNumber(emptyFlag ? 0 : sessionPage.getPageable().getPageNumber());

        Response<SessionDTO> response = new Response<>();
        List<SessionDTO> contentData = emptyFlag ? List.of() :
                sessionPage.stream().map(this::toDTO).collect(Collectors.toList());
        response.setEntityList(contentData);
        response.setMetadata(metadata);

        return response;
    }

    private SessionDTO toDTO(Session session) {
        SessionDTO result = new SessionDTO();
        result.setId(session.getId());
        result.setDateTime(session.getDateTime());
        result.setRoom(session.getRoom());
        result.setPrice(session.getPrice());
        result.setFilmId(session.getFilm().getId());
        return result;
    }
}
