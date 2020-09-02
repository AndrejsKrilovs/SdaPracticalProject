package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.mapper.PlaceMapper;
import lv.sda.cinemaapi.repository.PlaceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @AleksandrKozirev
 * 1. Make an AbstractController and extend from it.
 * 2. Please create an Service layer
 */
@RestController
@RequestMapping("/api/place.svc")
@CrossOrigin(origins="http://localhost:4200")
public class PlaceController {
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public PlaceController(PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    @GetMapping("/Places")
    public List<PlaceDTO> placeList() {
        return placeRepository.findAll()
                .stream()
                .map(placeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/Places({room_number})")
    public List<PlaceDTO> placeListByRoomNumber(@PathVariable("room_number") Integer roomNumber) {
        return placeRepository.findPlaceByRoom(Room.values()[roomNumber])
                .stream()
                .map(placeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/Place")
    public void updatePlaceData(
            @RequestParam("room_number") Integer roomNumber,
            @RequestParam("seat_number") Integer seatNumber
    ) {

    }
}
