package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.mapper.PlaceMapper;
import lv.sda.cinemaapi.service.PlaceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/place.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    @GetMapping(path = "/Places")
    public ResponseDTO<PlaceDTO> findPlacesBySession(@RequestParam(value = "session_id") Long sessionId) {
        return placeMapper.generateResponse(placeService.findPlacesBySession(sessionId));
    }

    @PutMapping(value = "/Place")
    public PlaceDTO updatePlace(@RequestBody PlaceDTO placeDTO) {
        Place placeToUpdate = placeMapper.fromDTO(placeDTO);
        Place updatedPlace = placeService.changePlaceStatus(placeToUpdate);
        return placeMapper.generatePlace(updatedPlace);
    }
}
