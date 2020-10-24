package lv.sda.cinemaapi.service;

import com.google.common.collect.ImmutableList;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.PlacePrimaryKey;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.repository.PlaceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PlaceServiceTest {

    private final PlaceRepository repository = Mockito.mock(PlaceRepository.class);
    private final PlaceService service = new PlaceService(repository);

    @Before
    public void setUp() throws Exception {
        Place place1 = new Place();
        place1.setAvailable(Boolean.FALSE);

        PlacePrimaryKey primaryKey1 = new PlacePrimaryKey();
        primaryKey1.setRoomNumber(Room.ONE);
        primaryKey1.setPlaceNumber(1);
        place1.setId(primaryKey1);

        Place place2 = new Place();
        place2.setAvailable(Boolean.FALSE);

        PlacePrimaryKey primaryKey2 = new PlacePrimaryKey();
        primaryKey2.setRoomNumber(Room.ONE);
        primaryKey2.setPlaceNumber(2);
        place1.setId(primaryKey2);

        doReturn(ImmutableList.of(place1, place2)).when(repository)
                .findPlaceByRoom(Room.ONE);
    }

    @Test
    public void placesByRoomNumber() {
        List<Place> placesList = service.placesByRoomNumber(1);
        assertEquals(2, placesList.size());
    }

    @Test
    public void updatePlace() {
        Place updatedEntity = new Place();
        updatedEntity.setAvailable(Boolean.TRUE);
        service.updatePlace(updatedEntity);

        verify(repository, times(1))
                .save(updatedEntity);
    }
}