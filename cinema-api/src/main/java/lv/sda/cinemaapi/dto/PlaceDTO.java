package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceDTO {
    @JsonProperty(value = "room_number")
    Integer roomNumber;

    @JsonProperty(value = "seat_number")
    Integer seatNumber;

    @JsonProperty(value = "place_availability")
    Boolean available;

    @JsonProperty(value = "place_enable")
    Boolean enabled = getAvailable();
}
