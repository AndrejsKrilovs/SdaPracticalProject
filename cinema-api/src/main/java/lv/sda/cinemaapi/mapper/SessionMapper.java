package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper extends AbstractMapper<SessionDTO, Session> {

    @Override
    protected SessionDTO generateDTO(Session entity) {
        return SessionDTO.builder()
                .roomNumber(entity.getRoomNumber())
                .dateTime(entity.getDateTime())
                .price(entity.getPrice())
                .id(entity.getId())
                .build();
    }

    @Override
    protected Session generateEntity(SessionDTO dto) {
        Session result = new Session();
//        result.setId(dto.getId());
//        result.setPrice(dto.getPrice());
//        result.setDateTime(dto.getDateTime());
        return result;
    }
}
