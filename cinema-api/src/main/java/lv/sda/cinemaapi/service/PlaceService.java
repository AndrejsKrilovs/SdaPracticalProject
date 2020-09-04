package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> placesByRoomNumber(Integer roomNumber) {
        return new ArrayList<>(placeRepository.findPlaceByRoom(Room.values()[roomNumber]));
    }

    public Place updatePlace(Place place) {
        return placeRepository.save(place);
    }
}
