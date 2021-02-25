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
        id.setRoom(Room.values()[placeDTO.getRoomNumber()]);
        id.setPlace(placeDTO.getPlaceNumber());
        result.setId(id);

        return result;
    }

    public PlaceDTO toDTO(Place place) {
        PlaceDTO result = new PlaceDTO();
        result.setAvailable(place.getAvailable());
        result.setRoomNumber(place.getId().getRoom().ordinal());
        result.setPlaceNumber(place.getId().getPlace());
        result.setEnabled(place.getAvailable());
        return result;
    }
}
