package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.PlacePrimaryKey;
import lv.sda.cinemaapi.entity.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceMapperTest {

    private final PlaceMapper placeMapper = new PlaceMapper();

    @Test
    public void fromDTO() {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setAvailable(Boolean.TRUE);
        placeDTO.setRoomNumber(3);
        placeDTO.setPlaceNumber(20);
        placeDTO.setEnabled(Boolean.FALSE);

        Place place = placeMapper.fromDTO(placeDTO);

        assertEquals(Boolean.TRUE, place.getAvailable());
        assertEquals(3, place.getId().getRoomNumber().ordinal());
        assertEquals(20, place.getId().getPlaceNumber());
    }

    @Test
    public void toDTO() {
        Place place = new Place();
        place.setAvailable(Boolean.TRUE );

        PlacePrimaryKey id = new PlacePrimaryKey();
        id.setRoomNumber(Room.values()[3]);
        id.setPlaceNumber(20);
        place.setId(id);

        PlaceDTO placeDTO = placeMapper.toDTO(place);

        assertEquals(Boolean.TRUE, placeDTO.getAvailable());
        assertEquals(3, placeDTO.getRoomNumber());
        assertEquals(20, placeDTO.getPlaceNumber());
        assertEquals(Boolean.TRUE, placeDTO.getEnabled());
    }
}