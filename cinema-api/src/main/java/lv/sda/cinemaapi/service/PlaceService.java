package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.Room;
import lv.sda.cinemaapi.mapper.PlaceMapper;
import lv.sda.cinemaapi.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public PlaceService(PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    public List<PlaceDTO> placesByRoomNumber(Integer roomNumber) {
        return placeRepository.findPlaceByRoom(Room.values()[roomNumber])
                .stream()
                .map(placeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PlaceDTO updatePlace(PlaceDTO placeDTO) {
        Place result = placeRepository.save(placeMapper.fromDTO(placeDTO));
        return placeMapper.toDTO(result);
    }
}
