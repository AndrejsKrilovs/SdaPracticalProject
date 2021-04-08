package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper extends AbstractMapper<PlaceDTO, Place>{

    @Override
    public PlaceDTO generateDTO(Place entity) {
        return PlaceDTO.builder()
                .roomNumber(entity.getId().getRoom())
                .seatNumber(entity.getId().getPlace())
                .available(entity.getAvailable())
                .build();
    }
}
