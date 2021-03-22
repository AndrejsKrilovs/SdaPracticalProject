package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lv.sda.cinemaapi.entity.Room;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Value
@Builder
public class PlaceDTO {
    @Positive
    @NotBlank
    @JsonProperty(value = "room_number")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    Room roomNumber;

    @Positive
    @NotBlank
    @JsonProperty(value = "place_seat")
    Integer placeNumber;

    @Positive
    @NotBlank
    @JsonProperty(value = "place_session_id")
    Long session;

    @JsonProperty(value = "place_availability")
    Boolean available;

    @JsonProperty(value = "enable")
    Boolean enabled = getAvailable();
}
