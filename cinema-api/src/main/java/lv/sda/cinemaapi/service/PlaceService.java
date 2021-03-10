package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<Place> placesByRoomNumber(Integer roomNumber) {
        Room room = (Room) Array.get(Room.values(), roomNumber);
        return placeRepository.findPlaceByRoom(room);
    }

    public Place changePlaceStatus(Place place) {
        return placeRepository.save(Objects.requireNonNull(place));
    }
}
