package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lv.sda.cinemaapi.entity.Room;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class PlaceDTO {
    @Positive
    @NotBlank
    @JsonProperty(value = "room_number")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Room roomNumber;

    @Positive
    @NotBlank
    @JsonProperty(value = "place_seat")
    private Integer placeNumber;

    @Positive
    @NotBlank
    @JsonProperty(value = "place_session_id")
    private Long session;

    @JsonProperty(value = "place_availability")
    private Boolean available;

    @JsonProperty(value = "enable")
    private Boolean enabled = getAvailable();
}
