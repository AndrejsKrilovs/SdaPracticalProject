package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.PlacePrimaryKey;
import org.springframework.stereotype.Component;

@Component
public class SessionPlaceMapper extends AbstractMapper<PlaceDTO, Place>{

    @Override
    protected PlaceDTO generateDTO(Place entity) {
        return PlaceDTO.builder()
                .roomNumber(entity.getId().getRoom())
                .seatNumber(entity.getId().getPlace())
                .available(entity.getAvailable())
                .build();
    }

    @Override
    protected Place generateEntity(PlaceDTO dto) {
        Place result  = new Place();
        result.setAvailable(dto.getAvailable());

        PlacePrimaryKey primaryKey = new PlacePrimaryKey();
        primaryKey.setRoom(dto.getRoomNumber());
        primaryKey.setPlace(dto.getSeatNumber());
        result.setId(primaryKey);
        return result;
    }
}
