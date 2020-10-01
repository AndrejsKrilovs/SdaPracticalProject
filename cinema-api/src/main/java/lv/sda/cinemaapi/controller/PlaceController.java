package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.mapper.PlaceMapper;
import lv.sda.cinemaapi.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public ResponseEntity<List<PlaceDTO>> placeListByRoomNumber(@PathVariable("room_number") Integer roomNumber) {
        List<PlaceDTO> placeList = placeService.placesByRoomNumber(roomNumber)
                .stream()
                .map(placeMapper::toDTO)
                .collect(Collectors.toList());

        return placeList.size() > 0 ?
                new ResponseEntity<>(placeList, HttpStatus.OK) :
                new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/Place")
    public ResponseEntity<PlaceDTO> updatePlaceData(@Valid @RequestBody PlaceDTO placeDTO) {
        Place placeToUpdate = placeMapper.fromDTO(placeDTO);
        Place result = placeService.updatePlace(placeToUpdate);
        return new ResponseEntity<>(placeMapper.toDTO(result), HttpStatus.ACCEPTED);
    }
}
