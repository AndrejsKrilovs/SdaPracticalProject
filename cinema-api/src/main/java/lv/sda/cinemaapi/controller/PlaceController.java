package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.mapper.PlaceMapper;
import lv.sda.cinemaapi.service.PlaceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/place.svc")
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    @GetMapping(path = "/Places")
    public Response<PlaceDTO> findPlacesBySession(@RequestParam(value = "session_id") Long sessionId) {
        return placeMapper.generateResponse(placeService.findPlacesBySession(sessionId));
    }

    @PutMapping(value = "/Place")
    public PlaceDTO updatePlace(@RequestBody PlaceDTO placeDTO) {
        System.out.println(placeDTO);
        return placeDTO;
    }
//
//    @PutMapping("/Place")
//    public ResponseEntity<PlaceDTO> updatePlaceData(@Valid @RequestBody PlaceDTO placeDTO) {
//        Place placeToUpdate = placeMapper.fromDTO(placeDTO);
//        Place result = placeService.changePlaceStatus(placeToUpdate);
//        return new ResponseEntity<>(placeMapper.toDTO(result), HttpStatus.ACCEPTED);
//    }
}
