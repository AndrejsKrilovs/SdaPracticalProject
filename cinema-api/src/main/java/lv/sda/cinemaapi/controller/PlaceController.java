package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.mapper.PlaceMapper;
import lv.sda.cinemaapi.repository.PlaceRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
}
