package lv.sda.cinemaapi.mapper;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.PlacePrimaryKey;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceMapper {
    private final SessionRepository sessionRepository;

    public Response<PlaceDTO> generateResponse(Page<Place> placesPage) {
        boolean emptyFlag = placesPage.isEmpty();
        Metadata metadata = Metadata.builder()
                .pageNumber(emptyFlag ? 0 : placesPage.getPageable().getPageNumber())
                .totalPages(emptyFlag ? 0 : placesPage.getTotalPages())
                .totalElements(placesPage.getTotalElements())
                .offset(placesPage.getPageable().getOffset())
                .build();

        List<PlaceDTO> contentData = emptyFlag ? List.of() :
                placesPage.stream()
                        .map(this::generatePlace)
                        .collect(Collectors.toList());

        return Response.<PlaceDTO>builder()
                .entityList(contentData)
                .metadata(metadata)
                .build();
    }

    public Place fromDTO(PlaceDTO placeDTO) {
        Place result = new Place();
        result.setAvailable(placeDTO.getAvailable());

        PlacePrimaryKey id = new PlacePrimaryKey();
        id.setSession(sessionRepository.getOne(placeDTO.getSession()));
        id.setPlace(placeDTO.getPlaceNumber());
        result.setId(id);

        return result;
    }

    public PlaceDTO generatePlace(Place place) {
        return PlaceDTO.builder()
                .roomNumber(place.getId().getSession().getRoom())
                .session(place.getId().getSession().getId())
                .placeNumber(place.getId().getPlace())
                .available(place.getAvailable())
                .build();
    }
}
