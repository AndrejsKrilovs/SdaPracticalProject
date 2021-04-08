package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper extends AbstractMapper<SessionDTO, Session>{

    @Override
    public SessionDTO generateDTO(Session entity) {
        return SessionDTO.builder()
                .roomNumber(entity.getRoomNumber())
                .dateTime(entity.getDateTime())
                .price(entity.getPrice())
                .id(entity.getId())
                .build();
    }
}
