package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.PlacePrimaryKey;
import lv.sda.cinemaapi.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {
    public Place fromDTO(PlaceDTO placeDTO) {
        Place result = new Place();
        result.setAvailable(placeDTO.getAvailable());

        PlacePrimaryKey id = new PlacePrimaryKey();
        id.setRoomNumber(Room.values()[placeDTO.getRoomNumber()]);
        id.setPlaceNumber(placeDTO.getPlaceNumber());
        result.setId(id);

        return result;
    }

    public PlaceDTO toDTO(Place place) {
        PlaceDTO result = new PlaceDTO();
        result.setAvailable(place.getAvailable());
        result.setRoomNumber(place.getId().getRoomNumber().ordinal());
        result.setPlaceNumber(place.getId().getPlaceNumber());
        return result;
    }
}
