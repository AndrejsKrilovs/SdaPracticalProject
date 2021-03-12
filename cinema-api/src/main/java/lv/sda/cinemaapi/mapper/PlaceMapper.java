package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.Metadata;
import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.dto.Response;
import lv.sda.cinemaapi.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceMapper {
    public Response<PlaceDTO> generateResponse(Page<Place> placesPage) {
        boolean emptyFlag = placesPage.isEmpty();
        Metadata metadata = new Metadata();
        metadata.setTotalPages(emptyFlag ? 0 : placesPage.getTotalPages());
        metadata.setTotalElements(placesPage.getTotalElements());
        metadata.setOffset(placesPage.getPageable().getOffset());
        metadata.setPageNumber(emptyFlag ? 0 : placesPage.getPageable().getPageNumber());

        Response<PlaceDTO> response = new Response<>();
        List<PlaceDTO> contentData = emptyFlag ? List.of() :
                placesPage.stream().map(this::toDTO).collect(Collectors.toList());
        response.setEntityList(contentData);
        response.setMetadata(metadata);

        return response;
    }
//    public Place fromDTO(PlaceDTO placeDTO) {
//        Place result = new Place();
//        result.setAvailable(placeDTO.getAvailable());
//
//        PlacePrimaryKey id = new PlacePrimaryKey();
//        id.setRoom(Room.values()[placeDTO.getRoomNumber()]);
//        id.setPlace(placeDTO.getPlaceNumber());
//        result.setId(id);
//
//        return result;
//    }
//
    private PlaceDTO toDTO(Place place) {
        PlaceDTO result = new PlaceDTO();
        result.setEnabled(place.getAvailable());
        result.setAvailable(place.getAvailable());
        result.setPlaceNumber(place.getId().getPlace());
        result.setSession(place.getId().getSession().getId());
        result.setRoomNumber(place.getId().getSession().getRoom());
        return result;
    }
}
