package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/place.svc")
@CrossOrigin(origins="http://localhost:4200")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/Places({room_number})")
    public List<PlaceDTO> placeListByRoomNumber(@PathVariable("room_number") Integer roomNumber) {
        return placeService.placesByRoomNumber(roomNumber);
    }

    @PutMapping("/Place")
    public PlaceDTO updatePlaceData(@RequestBody PlaceDTO placeDTO) {
        return placeService.updatePlace(placeDTO);
    }
}
