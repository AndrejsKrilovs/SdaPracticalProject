package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.mapper.PlaceMapper;
import lv.sda.cinemaapi.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/place.svc")
@CrossOrigin(origins="http://localhost:4200")
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    public PlaceController(PlaceService placeService, PlaceMapper placeMapper) {
        this.placeService = placeService;
        this.placeMapper = placeMapper;
    }

    @GetMapping("/Places({room_number})")
    public List<PlaceDTO> placeListByRoomNumber(@PathVariable("room_number") Integer roomNumber) {
        return placeService.placesByRoomNumber(roomNumber)
                .stream()
                .map(placeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/Place")
    public PlaceDTO updatePlaceData(@RequestBody PlaceDTO placeDTO) {
        Place placeToUpdate = placeMapper.fromDTO(placeDTO);
        Place result = placeService.updatePlace(placeToUpdate);
        return placeMapper.toDTO(result);
    }
}
