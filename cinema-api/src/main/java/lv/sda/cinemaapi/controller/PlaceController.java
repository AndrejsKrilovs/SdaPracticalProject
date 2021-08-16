package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.service.PlaceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/place.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class PlaceController {
//    private final PlaceService placeService;
//
//    @GetMapping(path = "/Places")
//    public ResponseDTO<PlaceDTO> findPlacesBySession(
//            @RequestParam(value = "session_id") Long sessionId
//    ){
//        return placeService.findPlacesBySession(sessionId);
//    }
}
