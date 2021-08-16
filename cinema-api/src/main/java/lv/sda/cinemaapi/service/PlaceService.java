package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.dto.ResponseDTO;
import lv.sda.cinemaapi.mapper.SessionPlaceMapper;
import lv.sda.cinemaapi.repository.PlaceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {
//    private final PlaceRepository placeRepository;
//    private final SessionPlaceMapper sessionPlaceMapper;
//
//    public ResponseDTO<PlaceDTO> findPlacesBySession(Long sessionId) {
//        return sessionPlaceMapper.generateResponse(
//                placeRepository.findPlaceBySession(sessionId, PageRequest.of(0, 30))
//        );
//    }
}
