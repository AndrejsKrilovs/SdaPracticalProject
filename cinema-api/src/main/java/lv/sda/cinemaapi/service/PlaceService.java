package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.repository.PlaceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Page<Place> findPlacesBySession(Long sessionId) {
        return placeRepository.findPlaceBySession(sessionId, PageRequest.of(0, 30));
    }

    public Place changePlaceStatus(Place place) {
        return placeRepository.save(place);
    }
}
